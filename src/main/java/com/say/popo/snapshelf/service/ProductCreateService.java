package com.say.popo.snapshelf.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.say.popo.snapshelf.dto.PostResult;
import com.say.popo.snapshelf.entity.AIDescription;
import com.say.popo.snapshelf.entity.Product;
import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.AIDescriptiontRepository;
import com.say.popo.snapshelf.repository.ProductRepository;
import com.say.popo.snapshelf.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductCreateService {
	
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final AIDescriptiontRepository aiDescriptionRepository;
	private final PromptService promptService;
	private final VisionService visionService;
	private final GeminiService geminiService;
	
	public ProductCreateService(ProductRepository productRepository, UserRepository userRepository, AIDescriptiontRepository aiDescriptionRepository,PromptService promptService,
			VisionService visionService, GeminiService geminiService) {
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.aiDescriptionRepository = aiDescriptionRepository;
		this.promptService = promptService;
		this.visionService = visionService;
		this.geminiService = geminiService;
	}


	public PostResult saveProduct(MultipartFile file, String name, int price, int stock,RedirectAttributes redirectAttributes) throws IOException {

		String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
		Path uploadPath = Paths.get("src/main/resources/static/uploads/" + filename);
		Files.copy(file.getInputStream(),uploadPath);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Users user = userRepository.findByEmail(email).orElseThrow();
		
		//画像、商品名、価格、在庫を保存
		Product product = new Product();
		product.setImage_url("/uploads/" + filename);
		product.setCaption(name);
		product.setUser(user);
		product.setPrice(price);
		product.setStock(stock);
		productRepository.save(product);
		System.out.println("登録画面からの投稿完了");
		
		//画像解析
		String fullUrl = "http://localhost:8080/uploads/" + filename;
		List<String> tags = visionService.analyzeImageByUrl(fullUrl);
		//プロンプト作成
		String prompt = promptService.buildPrompt(tags,name);
		System.out.println("受け取ったプロンプト：" + prompt);
		//AIメッセージ生成
		String discription = geminiService.callGeminiApi(prompt);
		System.out.println("受け取った説明文：" + discription);
		
		//AIメッセージをDBに保存
		AIDescription aiDescription = new AIDescription();
		aiDescription.setProduct(product);
		aiDescription.setContent(discription);
		aiDescriptionRepository.save(aiDescription);
		
		

		return new PostResult(discription,"/uploads/" + filename, aiDescription.getId());

	}
	
	public void updateDescription(Long id,String desc) {
		Optional<AIDescription> optional = aiDescriptionRepository.findById(id);
		if(optional.isPresent()) {
			AIDescription entity = optional.get();
			entity.setContent(desc);
			aiDescriptionRepository.save(entity);
		}
	}
}

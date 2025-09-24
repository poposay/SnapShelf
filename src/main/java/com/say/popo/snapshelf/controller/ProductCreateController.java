package com.say.popo.snapshelf.controller;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.say.popo.snapshelf.dto.PostResult;
import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.UserRepository;
import com.say.popo.snapshelf.service.ProductCreateService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductCreateController {
	private final ProductCreateService productCreateService;
	private final UserRepository userRepository;
	
	public ProductCreateController(ProductCreateService productCreateService, UserRepository userRepository) {
		this.productCreateService = productCreateService;
		this.userRepository = userRepository;
	}

	@GetMapping("/productcreate")
	public String showPostPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Users user = userRepository.findByEmail(email).orElseThrow();
		model.addAttribute("currentUsername",user.getUsername());
		return "post";
	}
	
	@GetMapping("/tutorial") 
		public String showTutorialPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Users user = userRepository.findByEmail(email).orElseThrow();
		model.addAttribute("currentUsername",user.getUsername());
			return "tutorial";
		}
	
	
	@PostMapping("/productcreate")
	public String handlePostAndResirect(@RequestParam("productimage") MultipartFile file ,
					@RequestParam String name,@RequestParam int price,@RequestParam int stock,
					@RequestParam(value = "redirectTo", required=false, defaultValue="prodictconfirmation") String redirectTo,
					HttpSession session,RedirectAttributes redirectAttributes) throws IOException {
		if(!file.isEmpty()) {
			//画像・コメント保存とDB登録の処理をサービスへ移譲
			System.out.println("saveProductt呼び出し");
			
			PostResult result = productCreateService.saveProduct(file,name,price,stock,redirectAttributes);
			System.out.println("PostResultで受け取った内容：" + result.getAIDescription() + result.getImageUrl());
			
			redirectAttributes.addFlashAttribute("popoMessage",result.getAIDescription());
			redirectAttributes.addFlashAttribute("imageUrl",result.getImageUrl());
		
			return "redirect:/" + redirectTo;
		}else {
			return "error";
		}
	}
}

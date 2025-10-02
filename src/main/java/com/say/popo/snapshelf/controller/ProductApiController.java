package com.say.popo.snapshelf.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.say.popo.snapshelf.dto.DescriptionUpdateRequest;
import com.say.popo.snapshelf.dto.PostResult;
import com.say.popo.snapshelf.dto.ProductDto;
import com.say.popo.snapshelf.entity.AIDescription;
import com.say.popo.snapshelf.entity.Product;
import com.say.popo.snapshelf.repository.AIDescriptiontRepository;
import com.say.popo.snapshelf.repository.ProductRepository;
import com.say.popo.snapshelf.service.ProductService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {

	private ProductService productService;
	private final ProductRepository productRepository;
	private final AIDescriptiontRepository aiDescriptionRepository;
	
	public ProductApiController(ProductService productService,ProductRepository productRepository,
			AIDescriptiontRepository aiDescriptionRepository) {
		this.productService = productService;
		this.productRepository = productRepository;
		this.aiDescriptionRepository = aiDescriptionRepository;
	}

	@PostMapping("/update-description")
	public ResponseEntity<?> updateDescription(@RequestBody DescriptionUpdateRequest req) {
		try {
			System.out.println("受け取ったID：" + req.getId() + "受け取った説明文：" + req.getDescription());
			//説明文の保存と公開フラグ設定をサービスに移譲
			productService.updateDescriptionAndPublish(req.getId(), req.getDescription());
			
		return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
}
	@PostMapping("/productcreate")
	public ResponseEntity<PostResult> handlePostAndReturnJson(
			@RequestParam("productimage") MultipartFile file ,
			@RequestParam String name,
			@RequestParam int price,
			@RequestParam int stock) throws IOException {
		
		if(!file.isEmpty()) {
			//画像・コメント保存とDB登録の処理をサービスへ移譲
			System.out.println("saveProduct呼び出し");
			
			PostResult result = productService.saveProduct(file,name,price,stock);
			System.out.println("PostResultで受け取った内容：" + result.getAiDescription() + result.getAiDescriptionId());
			
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/shelfview")
	public Page<ProductDto> getAllProducts(@RequestParam(defaultValue = "0") int page,
											@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable)
				.map(ProductDto::new);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
		
		System.out.println("受け取ったID：" + id);
		
		return productRepository.findById(id)
				.map(product -> ResponseEntity.ok(new ProductDto(product)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		Product product = productRepository.findById(id).orElse(null);
		
		//関連する説明文を先に削除
		AIDescription desc = aiDescriptionRepository.findByProduct(product);
		aiDescriptionRepository.delete(desc);
		
		productRepository.delete(product);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> apdateProduct(@PathVariable Long id,
			@RequestParam(value = "productimage", required = false) MultipartFile file ,
			@RequestParam String name,
			@RequestParam int price,
			@RequestParam int stock,
			@RequestParam String description) throws IOException  {
		
		try {
			System.out.println("更新する内容：" + id + file + name + price + stock + description);
			//更新をサービスへ移譲
			productService.updateProduct(id,file,name,price,stock,description);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
	}
	}
}

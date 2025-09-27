package com.say.popo.snapshelf.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.say.popo.snapshelf.dto.ProductDto;
import com.say.popo.snapshelf.entity.Product;
import com.say.popo.snapshelf.repository.ProductRepository;
import com.say.popo.snapshelf.service.ProductCreateService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductApiController {

	@Autowired
	private ProductCreateService productCreateService;
	private final ProductRepository productRepository;
	
	public ProductApiController(ProductCreateService productCreateService,ProductRepository productRepository) {
		this.productCreateService = productCreateService;
		this.productRepository = productRepository;
	}
	
	@PostMapping("/update-description")
	public ResponseEntity<?> updateDescription(HttpEntity<String> httpEntity) {

		try {
			String json = httpEntity.getBody();
			System.out.println("受け取ったJSON文字列：" + json);
			
			//Jackson ObjectMapperを使ってJSONをパース
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> payload = objectMapper.readValue(json,Map.class);
			
			//パースしたMapから値を取得
			long id = Long.parseLong(payload.get("id").toString());
			String desc = payload.get("description").toString();
			
			System.out.println("id：" + id + " description:" + desc);

		
			productCreateService.updateDescription(id,desc);

		return ResponseEntity.ok().build();
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/products")
	public Page<ProductDto> getAllProducts(@RequestParam(defaultValue = "0") int page,
											@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable)
				.map(ProductDto::new);
	}
	
}

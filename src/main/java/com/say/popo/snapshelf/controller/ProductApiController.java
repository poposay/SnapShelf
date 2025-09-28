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
import com.say.popo.snapshelf.dto.DescriptionUpdateRequest;
import com.say.popo.snapshelf.dto.ProductDto;
import com.say.popo.snapshelf.entity.AIDescription;
import com.say.popo.snapshelf.entity.Product;
import com.say.popo.snapshelf.repository.AIDescriptiontRepository;
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

	private ProductCreateService productCreateService;
	private final ProductRepository productRepository;
	private final AIDescriptiontRepository aiDescriptionRepository;
	
	public ProductApiController(ProductCreateService productCreateService,ProductRepository productRepository,
			AIDescriptiontRepository aiDescriptionRepository) {
		this.productCreateService = productCreateService;
		this.productRepository = productRepository;
		this.aiDescriptionRepository = aiDescriptionRepository;
	}
	//DTOで受け取るver
/*	@PostMapping("/update-description")
	public ResponseEntity<?> updateDescription(@RequestBody DescriptionUpdateRequest req) {
		try {
			System.out.println("受け取ったID：" + req.getId());
		AIDescription desc = aiDescriptionRepository.findById(req.getId()).orElseThrow();
		desc.setEdited_description(req.getDescription());
		Product product = desc.getProduct();
		product.setIs_published(true); //商品情報を公開
		aiDescriptionRepository.save(desc);
		
		return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
}
	
	//HTTPEntityでうけとってパースするver	
/*	@PostMapping("/update-description")
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
*/	
	//HTTPEntityでMapとして受け取るver
	@PostMapping("/update-description")
	public ResponseEntity<?> updateDescription(HttpEntity<Map<String, Object>> httpEntity) {
		Map<String, Object> payload = httpEntity.getBody();
	    System.out.println("ペイロード：" + payload);
	    Long id = Long.valueOf(payload.get("id").toString());
	    String description = payload.get("description").toString();

	    System.out.println("ID：" + id);
	    System.out.println("説明文：" + description);

	    AIDescription desc = aiDescriptionRepository.findById(id).orElseThrow();
	    desc.setEdited_description(description);
	    Product product = desc.getProduct();
	    product.setIs_published(true);
	    aiDescriptionRepository.save(desc);

	    return ResponseEntity.ok().build();
	}

	@GetMapping("/products")
	public Page<ProductDto> getAllProducts(@RequestParam(defaultValue = "0") int page,
											@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable)
				.map(ProductDto::new);
	}
	
}

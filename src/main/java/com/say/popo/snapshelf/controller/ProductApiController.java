package com.say.popo.snapshelf.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.say.popo.snapshelf.service.ProductCreateService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductApiController {

	@Autowired
	private ProductCreateService productCreateService;
	
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
}

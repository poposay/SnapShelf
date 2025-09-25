package com.say.popo.snapshelf.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.say.popo.snapshelf.service.ProductCreateService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class ProductApiController {

	@Autowired
	private ProductCreateService productCreateService;
	
	@PostMapping("/update-description")
	public ResponseEntity<?> updateDescription(@RequestBody Map<String, String> body) {
		Long id = Long.parseLong(body.get("id"));
		String desc = body.get("description");
		productCreateService.updateDescription(id,desc);
		return ResponseEntity.ok().build();
	}
}

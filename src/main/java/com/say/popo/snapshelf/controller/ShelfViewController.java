package com.say.popo.snapshelf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.say.popo.snapshelf.entity.AIDescription;
import com.say.popo.snapshelf.entity.Category;
import com.say.popo.snapshelf.entity.Product;
import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.AIDescriptiontRepository;
import com.say.popo.snapshelf.repository.ProductRepository;
import com.say.popo.snapshelf.repository.UserRepository;
import com.say.popo.snapshelf.service.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShelfViewController {
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final AIDescriptiontRepository aiDescriptionRepository;
	private final CategoryService categoryService;
	private final ObjectMapper objectMapper;
	
	public ShelfViewController(UserRepository userRepository, ProductRepository productRepository,
								AIDescriptiontRepository aiDescriptionRepository, CategoryService categoryService, ObjectMapper objectMapper) {
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.aiDescriptionRepository = aiDescriptionRepository;
		this.categoryService = categoryService;
		this.objectMapper = objectMapper;
	}
	
	
	@GetMapping("/shelfview")
	public String showAlbumPage(Model model) {
		// ユーザー情報を取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Optional<Users> userOpt = userRepository.findByEmail(email);
		// ログインユーザー名をmodelに追加
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
			
			// カテゴリリストを取得しmodelに追加
			List<Category> categories = categoryService.findAll();
			try {
				model.addAttribute("categoriesJson", objectMapper.writeValueAsString(categories));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}	
			return "shelfview";
		
		}else {
			//ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
		
	}

}

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

import com.say.popo.snapshelf.entity.AIDescription;
import com.say.popo.snapshelf.entity.Product;
import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.AIDescriptiontRepository;
import com.say.popo.snapshelf.repository.ProductRepository;
import com.say.popo.snapshelf.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShelfViewController {
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final AIDescriptiontRepository aiDescriptionRepository;
	
	public ShelfViewController(UserRepository userRepository, ProductRepository productRepository, AIDescriptiontRepository aiDescriptionRepository) {
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.aiDescriptionRepository = aiDescriptionRepository;
	}
	
	
	@GetMapping("/shelfview")
	public String showAlbumPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
			
			
			List<Product> products = productRepository.findAll();

			model.addAttribute("products", products);
			
			return "shelfview";
		
		}else {
			//ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
		
	}

}

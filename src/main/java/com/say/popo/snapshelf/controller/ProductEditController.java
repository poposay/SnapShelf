package com.say.popo.snapshelf.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.say.popo.snapshelf.entity.Product;
import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.ProductRepository;
import com.say.popo.snapshelf.repository.UserRepository;

@Controller
public class ProductEditController {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	
	public ProductEditController(ProductRepository productRepository, UserRepository userRepository) {
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/products/{id}/edit")
	public String editProduct(@PathVariable Long id, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
		}
			
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			model.addAttribute("product",product);
			return "products/edit";
		}
		return "error/404";
	}
}
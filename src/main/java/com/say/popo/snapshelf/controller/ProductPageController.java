package com.say.popo.snapshelf.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.say.popo.snapshelf.dto.PostResult;
import com.say.popo.snapshelf.entity.Product;
import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.ProductRepository;
import com.say.popo.snapshelf.repository.UserRepository;
import com.say.popo.snapshelf.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductPageController {
	private final ProductService productCreateService;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	
	public ProductPageController(ProductService productCreateService, UserRepository userRepository, ProductRepository productRepository) {
		this.productCreateService = productCreateService;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	@GetMapping("/productcreate")
	public String showPostPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();		
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
		
			return "productcreate";
		}else {
			//ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
	}
	
	@GetMapping("/tutorial") 
		public String showTutorialPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
			return "tutorial";
		}else {
			//ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
	}
	
	@GetMapping("/products/{id}/detail")
	public String showProductDetail(@PathVariable Long id, Model model) {
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
			return "products/detail"; 
		}
		return "error/404";
	}
	
	@GetMapping("products/{id}/edit")
	public String showProductEditPage(@PathVariable Long id, Model model) {
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

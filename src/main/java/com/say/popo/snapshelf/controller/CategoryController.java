package com.say.popo.snapshelf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.say.popo.snapshelf.entity.Category;
import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.UserRepository;
import com.say.popo.snapshelf.service.CategoryService;

@Controller
public class CategoryController {
	
	private final UserRepository userRepository;
	private final CategoryService categoryService;
	
	public CategoryController(UserRepository userRepository, CategoryService categoryService) {
		this.userRepository = userRepository;
		this.categoryService = categoryService;
	}

	@GetMapping("/categories")
	public String showCategoryList(Model model) {
		
		List<Category> categories;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
			
		categories = categoryService.findAll();
		model.addAttribute("categories", categories);
			
			return "category/list";
			
		}else {
			//ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
	}
	
	@GetMapping("/categorycreate")
	public String showCategoryCreatePage(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
			return "category/create";
		}else {
			//ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
	}
	
	/* 実装途中
	@PostMapping
	public String CreateCategory()
	*/
}

package com.say.popo.popoalbum.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.repository.UserRepository;

@Controller
public class HomeController {
	
	private final UserRepository userRepository;
	
	public HomeController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/home")
	public String showHomePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Users user = userRepository.findByEmail(email).orElseThrow();
		model.addAttribute("currentUsername",user.getUsername());
		
		return "/home";
		
	}

}

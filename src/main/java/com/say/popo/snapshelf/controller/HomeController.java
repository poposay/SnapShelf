package com.say.popo.snapshelf.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.UserRepository;

@Controller
public class HomeController {
	
	private final UserRepository userRepository;
	
	public HomeController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
		    
	    @GetMapping("/")
	    public String home() {
	        return "redirect:/login";
	    }
	
	
	@GetMapping("/home")
	public String showHomePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
			return "home";
		}else {
			//ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
	}
	
	@GetMapping("/comingsoon")
	public String showcomingsoon(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
			return "/error/comingsoon";
		}else {
			//ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
	}
}

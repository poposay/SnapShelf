package com.say.popo.snapshelf.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.UserRepository;

@Controller
public class Tutorial2Controller {
	
	private final UserRepository userRepository;
	
	public Tutorial2Controller(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/tutorial2")
	public String showFirstMessagePage(Model model,@ModelAttribute("aiDescription") String aiDescription,
			@ModelAttribute("imageUrl") String imageUrl) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
			return "tutorial2";
		}else {
			//ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
	}

}

package com.say.popo.snapshelf.controller;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.say.popo.snapshelf.dto.RegisterRequest;
import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute @Valid RegisterRequest request,BindingResult result,Model model) {
		System.out.println("registerUserメソッド呼び出し");
		
		if(result.hasErrors()) {
			return "register";
		}
		
		if (userRepository.existsByEmail(request.getEmail())) {
			model.addAttribute("error","このメールアドレスは既に使用されています");
			return "register";
		}
		System.out.println("入力されたユーザー名：" + request.getUsername() + "メールアドレス：" + request.getEmail() + "パスワード" + request.getPassword());
		Users user = new Users();
		user.setUsername(request.getUsername());
		user.setPassword_hash(passwordEncoder.encode(request.getPassword()));
		user.setEmail(request.getEmail());
		user.setLast_login(LocalDateTime.now());
		
		userRepository.save(user);
		
		return "redirect:/login?registered=true";
	}

}

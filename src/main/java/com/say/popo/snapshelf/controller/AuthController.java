package com.say.popo.snapshelf.controller;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.UserRepository;

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
	public String showRegisterPage() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestParam String username,@RequestParam String email, @RequestParam String password,Model model) {
		System.out.println("registerUserメソッド呼び出し");
		//バリデーション
		if (userRepository.existsByEmail(email)) {
			model.addAttribute("error","メールアドレスは既に使用されています");
			return "register";
		}
		System.out.println("入力されたユーザー名：" + username + "メールアドレス：" + email + "パスワード" + password);
		Users user = new Users();
		user.setUsername(username);
		user.setPassword_hash(passwordEncoder.encode(password));
		user.setEmail(email);
		user.setLast_login(LocalDateTime.now());
		
		userRepository.save(user);
		
		return "redirect:/login?registered=true";
	}

}

/*
package com.say.popo.popoalbum.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.say.popo.popoalbum.dto.RegisterRequest;
import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.service.UserService;

@RestController
@RequestMapping("/api")
public class RegisterApiController {

	private final UserService userService;
	
	
	public RegisterApiController(UserService userService) {
		this.userService = userService;	
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request,
										  BindingResult bindingResult,HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getFieldError().getDefaultMessage();
			return ResponseEntity.badRequest().body(errorMessage);
		}
		
		Users user = userService.register(request);
		session.setAttribute("currentUser", user);
		session.setAttribute("userId", user.getId());
		return ResponseEntity.ok("登録が完了しました");
	}
}
*/
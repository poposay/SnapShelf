package com.say.popo.popoalbum.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private final UserRepository userRepository;
	
	public CustomLoginSuccessHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
	
		String email = authentication.getName();
		Users user = userRepository.findByEmail(email).orElse(null);
		
		if(user != null && user.isFirstLogin()) {
			user.setFirstLogin(false);
			userRepository.save(user);
			response.sendRedirect("/welcome");
		} else {
			response.sendRedirect("/home");
		}
	}
}

/*
package com.say.popo.popoalbum.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.say.popo.popoalbum.dto.RegisterRequest;
import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.repository.UserRepository;


@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Users register(RegisterRequest request) {
		if (userRepository.findByEmail(request.getEmail()) != null) {
	        throw new IllegalArgumentException("このメールアドレスは既に登録されています");
	    }

		
		Users user = new Users();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		String rawPassword = request.getPassword();
		String hashedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword_hash(hashedPassword);
		user.setLast_login(LocalDateTime.now());
		

		userRepository.save(user);
		return user;
	}
}
*/

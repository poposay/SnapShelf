package com.say.popo.snapshelf.service;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.UserRepository;
import com.say.popo.snapshelf.security.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;


	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("User not found:" + email));
		
		return new CustomUserDetails(user);
	}
	
	
}

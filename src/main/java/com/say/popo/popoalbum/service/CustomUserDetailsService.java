package com.say.popo.popoalbum.service;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.repository.UserRepository;

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
		
		UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(email);
		builder.password(user.getPassword_hash());
		builder.roles("USER");
		
		return builder.build();
	}
	
	
}

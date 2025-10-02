package com.say.popo.snapshelf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.say.popo.snapshelf.security.CustomAccessDeniedHandler;
import com.say.popo.snapshelf.security.CustomLoginSuccessHandler;
import com.say.popo.snapshelf.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;
	private final CustomLoginSuccessHandler customLoginSuccessHandler;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	private CustomAccessDeniedHandler accessDeniedHandler;

	
	public SecurityConfig(CustomUserDetailsService customUserDetailsService,CustomLoginSuccessHandler customLoginSuccessHandler) {
		this.customUserDetailsService = customUserDetailsService;
		this.customLoginSuccessHandler = customLoginSuccessHandler;
	}
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(authz -> authz
					.requestMatchers("/","/login","/register","/css/**","/js/**","/images/**","/swagger-ui/**","v3/api-docs/**").permitAll()
					.anyRequest().authenticated()
			)
		
			.formLogin(form -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.successHandler(customLoginSuccessHandler)
				.failureUrl("/login?error=true")
			)
		
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.permitAll()
			)
			.exceptionHandling(ex -> ex
					.accessDeniedHandler(accessDeniedHandler)
					)
			.csrf(csrf -> csrf.disable());
			
		return http.build();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}

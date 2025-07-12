package com.say.popo.popoalbum;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.repository.UserRepository;

@SpringBootApplication
public class PopoAlbumApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopoAlbumApplication.class, args);
	}
	@Bean
	 public CommandLineRunner testSave(UserRepository userRepository) {
	        return args -> {
	            Users user = new Users();
	            user.setUsername("Saaya");
	            user.setEmail("saaya@example.com");
	            user.setLast_login(LocalDateTime.now());
	            user.setPassword_hash("hashed_password_123");
	            userRepository.save(user);

	            System.out.println("保存完了！");
	        };
	    }
	

}

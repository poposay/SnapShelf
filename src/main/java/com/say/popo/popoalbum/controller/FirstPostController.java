package com.say.popo.popoalbum.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.say.popo.popoalbum.entity.Post;
import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.repository.PostRepository;
import com.say.popo.popoalbum.repository.UserRepository;

@Controller
public class FirstPostController {
	
	PostRepository postRepository;
	UserRepository userRepository;
	
	public FirstPostController(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/firstpost")
	public String showFirstPostPage() {
		return "firstpost";
	}
	
	@PostMapping("/firstpost")
	public String handleImageUpload(@RequestParam("memory") MultipartFile file,HttpSession session) throws IOException {
		if(!file.isEmpty()) {
			String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
			Path uploadPath = Paths.get("src/main/resources/static/uploads/" + filename);
			Files.copy(file.getInputStream(), uploadPath);
			
			//セッションからuser_idを取り出す
			Long userId = (Long) session.getAttribute("userId");
			Users user = userRepository.findById(userId).orElseThrow();
			
			//DBにパスを渡す
			Post post = new Post();
			post.setImage_url("/uploads/" + filename);
			post.setUser(user);
			System.out.println("postにセットされたユーザーID" + post.getUser().getId());
			
			postRepository.save(post);
			
			System.out.println("セッション中の userId: " + userId);
			System.out.println("取得したユーザー" + user);
			System.out.println("ユーザーID" + user.getId());
			
			return "redirect:/firstcomment";
			
		} else {
			return "redirect:/error";
		}
	}

}

package com.say.popo.popoalbum.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.say.popo.popoalbum.entity.Post;
import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.repository.PostRepository;
import com.say.popo.popoalbum.repository.UserRepository;


import jakarta.servlet.http.HttpSession;

@Service
public class PostService {
	
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final MessageService messageService;
	
	public PostService(PostRepository postRepository, UserRepository userRepository, MessageService messageService) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
		this.messageService = messageService;
	}

	public void savePost(MultipartFile file, String caption, HttpSession session, Model model) throws IOException {
		String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
		Path uploadPath = Paths.get("src/main/resources/static/uploads/" + filename);
		Files.copy(file.getInputStream(),uploadPath);
		
		Long userId = (Long)session.getAttribute("userId");
		Users user = userRepository.findById(userId).orElseThrow();
		
		Post post = new Post();
		post.setImage_url("/uploads/" + filename);
		post.setCaption(caption);
		post.setUser(user);
		
		postRepository.save(post);
		
		//メッセージ生成処理へ
		String fullUrl = "http://localhost:8080" + uploadPath;
		String popoMessage = messageService.generateMessage(fullUrl, caption);
		model.addAttribute("popoMessage", popoMessage);
	}
}

package com.say.popo.popoalbum.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.say.popo.popoalbum.entity.Post;
import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.repository.PostRepository;
import com.say.popo.popoalbum.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AlbumController {
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	
	public AlbumController(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	
	@GetMapping("/album")
	public String showAlbumPage(Model model, HttpSession session) {
		Long userId = (Long)session.getAttribute("userId");
		Users user = userRepository.findById(userId).orElseThrow();
		
		List<Post> posts = postRepository.findByUser(user);
		model.addAttribute("posts",posts);
		
		return "album";
	}

}

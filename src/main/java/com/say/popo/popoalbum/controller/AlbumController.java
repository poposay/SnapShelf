package com.say.popo.popoalbum.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.say.popo.popoalbum.entity.AIComment;
import com.say.popo.popoalbum.entity.Post;
import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.repository.AICommentRepository;
import com.say.popo.popoalbum.repository.PostRepository;
import com.say.popo.popoalbum.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AlbumController {
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final AICommentRepository aiCommentRepository;
	
	public AlbumController(UserRepository userRepository, PostRepository postRepository, AICommentRepository aiCommentRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
		this.aiCommentRepository = aiCommentRepository;
	}
	
	
	@GetMapping("/album")
	public String showAlbumPage(Model model, HttpSession session) {
		Long userId = (Long)session.getAttribute("userId");
		Users user = userRepository.findById(userId).orElseThrow();
		
		List<Post> posts = postRepository.findByUser(user);
		for (Post post : posts) {
		AIComment aiComment = aiCommentRepository.findByPost(post);
		}
		
		model.addAttribute("posts",posts);
		
		return "album";
	}

}

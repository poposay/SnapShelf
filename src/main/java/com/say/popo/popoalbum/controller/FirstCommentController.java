package com.say.popo.popoalbum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.say.popo.popoalbum.entity.Post;
import com.say.popo.popoalbum.repository.PostRepository;

@Controller
public class FirstCommentController {
	
	@Autowired
	PostRepository postRepository;

	@GetMapping("/firstcomment") 
	public String showfirstCommentPage(Model model) {
		Post firstPost = postRepository.findTopByOrderByIdDesc();
		model.addAttribute("imagePath",firstPost.getImage_url());
		System.out.println("imagePath: " + firstPost.getImage_url());
		return "firstcomment";
		
	}
	@PostMapping("/firstcomment")
	public String handleFirstComment(@RequestParam("firstcomment") String comment) {
		Post firstPost = postRepository.findTopByOrderByIdDesc();
		String imageUrl = firstPost.getImage_url();
		
		return "redirect:/firstmessage?userComment=" + comment +"&imagePath=" +  imageUrl;
	}
}

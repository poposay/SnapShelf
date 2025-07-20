package com.say.popo.popoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstCommentController {

	@GetMapping("/firstcomment") 
	public String showfirstCommentPage() {
		return "firstcomment";
		
	}
	@PostMapping("/firstcomment")
	public String handleFirstComment(@RequestParam("firstcomment") String comment) {
		System.out.println("コメント:" + comment);
		
		return "redirect:/home";
	}
}

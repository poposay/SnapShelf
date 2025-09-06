package com.say.popo.popoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PostViewController {

	@GetMapping("/post")
	public String showPostPage() {
		return "/post";
	}
	
	@PostMapping("/post")
	public void uploadPost(@RequestParam("memory") MultipartFile file ,@RequestParam("caption") String caption) {
		System.out.println("投稿完了");
	}
}

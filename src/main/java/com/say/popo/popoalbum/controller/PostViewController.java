package com.say.popo.popoalbum.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class PostViewController {
	private final PostApiController postApiController;
	
	public PostViewController(PostApiController postApiController) {
		this.postApiController = postApiController;
	}

	@GetMapping("/post")
	public String showPostPage() {
		return "post";
	}
	
	@PostMapping("/post")
	public String handlePostAndResirect(@RequestParam("memory") MultipartFile file ,@RequestParam String caption,HttpSession session,org.springframework.ui.Model model) throws IOException {
		postApiController. handleImageUpload(file,caption,session,model);
		
		return "memorysaved";
	}
}

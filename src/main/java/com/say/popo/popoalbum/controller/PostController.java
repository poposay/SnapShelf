package com.say.popo.popoalbum.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.say.popo.popoalbum.dto.PostResult;
import com.say.popo.popoalbum.service.PostService;


import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	private final PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/post")
	public String showPostPage() {
		return "post";
	}
	
	@PostMapping("/post")
	public String handlePostAndResirect(@RequestParam("memory") MultipartFile file ,
					@RequestParam String caption,HttpSession session,RedirectAttributes redirectAttributes) throws IOException {
		if(!file.isEmpty()) {
			//画像・コメント保存とDB登録の処理をサービスへ移譲
			System.out.println("savePost呼び出し");
			
			PostResult result = postService.savePost(file,caption,session,redirectAttributes);
			System.out.println("PostResultで受け取った内容：" + result.getPopoMessage() + result.getImageUrl());
			
			redirectAttributes.addFlashAttribute("popoMessage",result.getPopoMessage());
			redirectAttributes.addFlashAttribute("imageUrl",result.getImageUrl());
			
			return "redirect:/memorysaved";
		}else {
			return "error";
		}
	}
}

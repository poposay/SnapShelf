package com.say.popo.popoalbum.controller;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.say.popo.popoalbum.dto.PostResult;
import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.repository.UserRepository;
import com.say.popo.popoalbum.service.PostService;


import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	private final PostService postService;
	private final UserRepository userRepository;
	
	public PostController(PostService postService, UserRepository userRepository) {
		this.postService = postService;
		this.userRepository = userRepository;
	}

	@GetMapping("/post")
	public String showPostPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Users user = userRepository.findByEmail(email).orElseThrow();
		model.addAttribute("currentUsername",user.getUsername());
		return "post";
	}
	
	@GetMapping("/tutorial") 
		public String showTutorialPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Users user = userRepository.findByEmail(email).orElseThrow();
		model.addAttribute("currentUsername",user.getUsername());
			return "tutorial";
		}
	
	
	@PostMapping("/post")
	public String handlePostAndResirect(@RequestParam("memory") MultipartFile file ,
					@RequestParam String caption,
					@RequestParam(value = "redirectTo", required=false, defaultValue="memorysaved") String redirectTo,
					HttpSession session,RedirectAttributes redirectAttributes) throws IOException {
		if(!file.isEmpty()) {
			//画像・コメント保存とDB登録の処理をサービスへ移譲
			System.out.println("savePost呼び出し");
			
			PostResult result = postService.savePost(file,caption,redirectAttributes);
			System.out.println("PostResultで受け取った内容：" + result.getPopoMessage() + result.getImageUrl());
			
			redirectAttributes.addFlashAttribute("popoMessage",result.getPopoMessage());
			redirectAttributes.addFlashAttribute("imageUrl",result.getImageUrl());
		
			return "redirect:/" + redirectTo;
		}else {
			return "error";
		}
	}
}

package com.say.popo.popoalbum.controller;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.say.popo.popoalbum.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class PostApiController {
	
	private final PostService postService;
	
	public PostApiController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/post")
	public ResponseEntity<String> handleImageUpload(@RequestParam("memory") MultipartFile file,
			@RequestParam String caption, HttpSession session, Model model) throws IOException {
		System.out.println("PostApiControllerに到達");
		if(!file.isEmpty()) {
			//画像・コメント保存とDB登録の処理をサービスへ移譲
			System.out.println("savePost呼び出し");
			postService.savePost(file,caption,session,model);
			return ResponseEntity.ok("success");
		}else {
			return ResponseEntity.badRequest().body("error");
		}
	}
	

}

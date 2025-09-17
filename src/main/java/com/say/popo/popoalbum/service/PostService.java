package com.say.popo.popoalbum.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.say.popo.popoalbum.dto.PostResult;
import com.say.popo.popoalbum.entity.AIComment;
import com.say.popo.popoalbum.entity.Post;
import com.say.popo.popoalbum.entity.Users;
import com.say.popo.popoalbum.repository.AICommentRepository;
import com.say.popo.popoalbum.repository.PostRepository;
import com.say.popo.popoalbum.repository.UserRepository;


import jakarta.servlet.http.HttpSession;

@Service
public class PostService {
	
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final AICommentRepository aiCommentRepository;
	private final MessageService messageService;
	private final VisionService visionService;
	private final GeminiService geminiService;
	
	public PostService(PostRepository postRepository, UserRepository userRepository, AICommentRepository aiCommentRepository,MessageService messageService,
			VisionService visionService, GeminiService geminiService) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
		this.aiCommentRepository = aiCommentRepository;
		this.messageService = messageService;
		this.visionService = visionService;
		this.geminiService = geminiService;
	}


	public PostResult savePost(MultipartFile file, String caption, HttpSession session, RedirectAttributes redirectAttributes) throws IOException {

		String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
		Path uploadPath = Paths.get("src/main/resources/static/uploads/" + filename);
		Files.copy(file.getInputStream(),uploadPath);
		
		Long userId = (Long)session.getAttribute("userId");
		Users user = userRepository.findById(userId).orElseThrow();
		
		//画像とキャプションを保存
		Post post = new Post();
		post.setImage_url("/uploads/" + filename);
		post.setCaption(caption);
		post.setUser(user);
		postRepository.save(post);
		System.out.println("ポスト画面からの投稿完了");
		
		//画像解析
		String fullUrl = "http://localhost:8080/uploads/" + filename;
		List<String> tags = visionService.analyzeImageByUrl(fullUrl);
		//プロンプト作成
		String prompt = messageService.buildPrompt(tags,caption);
		System.out.println("受け取ったプロンプト：" + prompt);
		//AIメッセージ生成
		String popoMessage = geminiService.callGeminiApi(prompt);
		System.out.println("受け取ったメッセージ：" + popoMessage);
		
		//AIメッセージをDBに保存
		AIComment aiComment = new AIComment();
		aiComment.setPost(post);
		aiComment.setContent(popoMessage);
		aiCommentRepository.save(aiComment);
		
		

		return new PostResult(popoMessage,"/uploads/" + filename);

	}
}

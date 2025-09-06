package com.say.popo.popoalbum.service;

import org.springframework.stereotype.Service;

import com.say.popo.popoalbum.repository.PostRepository;

@Service
public class MessageService {
	
	/*private final VIsionServise visionService = new VisionService(); */
	private final GeminiService geminiService;
	private final PostRepository postRepository;
	
	public MessageService(GeminiService geminiService, PostRepository postRepository) {
		this.geminiService = geminiService;
		this.postRepository = postRepository;
	}
	
	public String generateMessage(String fullUrl, String caption) {
		/*
		①Visionでタグを抽出
		
		②プロンプト構築　*/
		String prompt = buildPrompt(/*tags,*/ caption);
		
		//デバッグ用
		System.out.println("生成されたプロンプト：" + prompt);
		
		//③Gemini1でコメント生成
		return geminiService.generateMessage(prompt);
	}
	
	private String buildPrompt(/*List<String> tags.*/ String caption) {
		return """
				投稿された画像には以下のタグが見つかりました：
				
				ユーザーコメント「%s」に対して、純粋で優しいオスの子犬のポポが、親しみをこめたコメントをしてください。
				""".formatted(/*String.join(",",tags),*/caption);
	}

}

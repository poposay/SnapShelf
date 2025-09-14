package com.say.popo.popoalbum.service;

import java.util.List;

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

	public String buildPrompt(List<String> tags, String caption) {
		//タグとキャプションからプロンプト構築
		StringBuilder prompt = new StringBuilder();
		prompt.append("以下の画像について、温かいメッセージを作成してください。\n");
		prompt.append("画像の特徴：").append(String.join(",", tags)).append("\n");
		prompt.append("ユーザーのコメント：").append(caption).append("\n");
		prompt.append("20文字以内で、親しみやすい口調でお願いします。");
		
		return prompt.toString();
	}

}

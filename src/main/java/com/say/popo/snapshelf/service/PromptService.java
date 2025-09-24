package com.say.popo.snapshelf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.say.popo.snapshelf.repository.ProductRepository;

@Service
public class PromptService {
	
	/*private final VIsionServise visionService = new VisionService(); */
	private final GeminiService geminiService;
	private final ProductRepository productRepository;
	
	public PromptService(GeminiService geminiService, ProductRepository productRepository) {
		this.geminiService = geminiService;
		this.productRepository = productRepository;
	}

	public String buildPrompt(List<String> tags, String caption) {
		//タグとキャプションからプロンプト構築
		StringBuilder prompt = new StringBuilder();
		prompt.append("以下の画像について、商品の色や形、用途についての説明文を作成してください。\n");
		prompt.append("画像の特徴：").append(String.join(",", tags)).append("\n");
		prompt.append("商品名：").append(caption).append("\n");
		prompt.append("50文字以内で、簡潔に特徴が伝わるように作成してください。");
		
		return prompt.toString();
	}

}

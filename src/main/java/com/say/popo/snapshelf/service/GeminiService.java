package com.say.popo.snapshelf.service;

import org.springframework.stereotype.Service;

@Service
public class GeminiService {

	public String callGeminiApi(String prompt) {
		//未実装
	
		String aiDescription = ("【タグ抽出・プロンプト作成まで実装済みのため、プロンプトを表示】" + prompt);
		return aiDescription;
	}
}

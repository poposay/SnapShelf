package com.say.popo.snapshelf.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {
	
	private final Client client;
	
	public GeminiService(Client client) {
		this.client = client;
	}
	
	public String callGeminiApi(String prompt) {
		GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash", prompt, null);
		
		return response.text();

	}
}

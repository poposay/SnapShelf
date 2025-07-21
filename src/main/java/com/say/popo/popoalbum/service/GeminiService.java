package com.say.popo.popoalbum.service;

import org.springframework.stereotype.Service;

import com.google.protobuf.ByteString;

@Service
public class GeminiService {

	public String generateMessage(String prompt) {
		//未実装
		ByteString promptBytes = ByteString.copyFromUtf8(prompt); // ← UTF-8でふわっと安心🐾
		
		return "ポポのメッセージ" + prompt;
	}
}

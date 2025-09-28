package com.say.popo.snapshelf.dto;

public class PostResult {
	private String aiDescription;
	private Long aiDescriptionId;
	
	public PostResult(String aiDescription, Long aiDescriptionId) {
		this.aiDescription = aiDescription;
		this.aiDescriptionId = aiDescriptionId;
	}
	
	
	public String getAiDescription() {
		return aiDescription;
	}

	
	public Long getAiDescriptionId() {
		return aiDescriptionId;
	}

}

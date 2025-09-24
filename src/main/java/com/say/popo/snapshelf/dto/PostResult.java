package com.say.popo.snapshelf.dto;

public class PostResult {
	private String aiDescription;
	private String imageUrl;
	
	public PostResult(String aiDescription, String imageUrl) {
		this.aiDescription = aiDescription;
		this.imageUrl = imageUrl;
	}
	
	public String getAIDescription() {
		return aiDescription;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

}

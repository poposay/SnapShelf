package com.say.popo.snapshelf.dto;

public class PostResult {
	private String aiDescription;
	private String imageUrl;
	private Long aiDescriptionId;
	
	public PostResult(String aiDescription, String imageUrl) {
		this.aiDescription = aiDescription;
		this.imageUrl = imageUrl;
	}
	
    public PostResult(String AIDescription, String imageUrl, Long aiDescriptionId) {
        this.aiDescription = AIDescription;
        this.imageUrl = imageUrl;
        this.aiDescriptionId = aiDescriptionId;
    }
	
	public String getAIDescription() {
		return aiDescription;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public Long getAIDescriptionId() {
		return aiDescriptionId;
	}

}

package com.say.popo.popoalbum.dto;

public class PostResult {
	private String popoMessage;
	private String imageUrl;
	
	public PostResult(String popoMessage, String imageUrl) {
		this.popoMessage = popoMessage;
		this.imageUrl = imageUrl;
	}
	
	public String getPopoMessage() {
		return popoMessage;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

}

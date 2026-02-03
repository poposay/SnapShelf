package com.say.popo.snapshelf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DescriptionUpdateRequest {
    @JsonProperty("descId")
	private Long descId;             // AIDescriptionのID
    

    @JsonProperty("description")
    private String description;  // 編集後の説明文
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private int price;
    
    @JsonProperty("stock")
    private int stock;
    
    
    public DescriptionUpdateRequest() {} // ← デフォルトコンストラクタ

    
    // getter & setter
    public Long getDescId() {
        return descId;
    }

    public void setDescId(Long descId) {
        this.descId = descId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}

package com.say.popo.snapshelf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DescriptionUpdateRequest {
    @JsonProperty("id")
	private Long id;             // AIDescriptionのID
    

    @JsonProperty("description")
    private String description;  // 編集後の説明文

    public DescriptionUpdateRequest() {} // ← デフォルトコンストラクタ

    
    // getter & setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

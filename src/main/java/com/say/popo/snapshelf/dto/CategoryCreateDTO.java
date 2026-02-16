package com.say.popo.snapshelf.dto;

import com.say.popo.snapshelf.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryCreateDTO {
	
	@NotBlank(message = "カテゴリ名を入力してください")
	@Size(max = 50, message = "カテゴリ名は50文字以内で入力してください")
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}

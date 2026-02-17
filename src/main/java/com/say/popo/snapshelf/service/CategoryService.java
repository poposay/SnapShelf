package com.say.popo.snapshelf.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.say.popo.snapshelf.dto.CategoryCreateDTO;
import com.say.popo.snapshelf.entity.Category;
import com.say.popo.snapshelf.repository.CategoryRepository;
import com.say.popo.snapshelf.security.CustomUserDetails;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category saveCategory(CategoryCreateDTO dto) {
		// ユーザー情報の取得
		Long userId = getCurrentUserId();
		
		Category category = new Category();
		category.setCategoryName(dto.getCategoryName());
		category.setCreatedBy(userId);
		
		return categoryRepository.save(category);
	}
	
	private Long getCurrentUserId() {
		// SpringSecurityから取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
	    return ((CustomUserDetails) userDetails).getId();
	}
	
	public boolean isDuplicate(String categoryName) {
	    return categoryRepository.existsByCategoryName(categoryName);
	}
}

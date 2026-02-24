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

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	// カテゴリの保存
	@Transactional
	public Category saveCategory(CategoryCreateDTO dto) {
		// ユーザー情報の取得
		Long userId = getCurrentUserId();
		
		Category category = new Category();
		category.setCategoryName(dto.getCategoryName());
		category.setCreatedBy(userId);
		
		return categoryRepository.save(category);
	}
	
	// ログインユーザーIDの取得
	private Long getCurrentUserId() {
		// SpringSecurityから取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
	    if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
	        throw new IllegalStateException("ユーザーが認証されていません");
	    }
	    
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
	    return ((CustomUserDetails) userDetails).getId();
	}
	
	// 重複チェック
	public boolean isDuplicate(String categoryName) {
	    return categoryRepository.existsByCategoryName(categoryName);
	}
	
	// 重複チェック（自分以外）
	public boolean isDuplicateIdNot(String categoryName, Long categoryId) {
		return categoryRepository.existsByCategoryNameAndIdNot(categoryName, categoryId);
	}
	
	// カテゴリのアップデート
	@Transactional
	public Category updateCategory(Long categoryId, CategoryCreateDTO dto) {
		Long userId = getCurrentUserId();
		
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new IllegalArgumentException("カテゴリが見つかりません"));
		category.setCategoryName(dto.getCategoryName());
		category.setUpdatedBy(userId);
		
		return categoryRepository.save(category);
	}
	
	// カテゴリの削除
	@Transactional
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}
}

package com.say.popo.snapshelf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.say.popo.snapshelf.entity.Category;

@Repository
public interface CategoryRepository  extends JpaRepository <Category, Long>{
	
	// カテゴリ名の重複チェック
	boolean existsByCategoryName(String categoryName);
	
	//　カテゴリ名の部分一致検索
	List<Category> findByCategoryNameContainingIgnoreCase(String keyword);
	
	// カテゴリ名の重複チェック（更新時）
	boolean existsByCategoryNameAndIdNot(String categoryName, Long id);
}

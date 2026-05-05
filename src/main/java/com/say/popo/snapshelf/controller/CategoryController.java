package com.say.popo.snapshelf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.say.popo.snapshelf.dto.CategoryCreateDTO;
import com.say.popo.snapshelf.dto.CategoryUpdateDTO;
import com.say.popo.snapshelf.entity.Category;
import com.say.popo.snapshelf.entity.Product;
import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.CategoryRepository;
import com.say.popo.snapshelf.repository.UserRepository;
import com.say.popo.snapshelf.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@Controller
public class CategoryController {

	private final UserRepository userRepository;
	private final CategoryService categoryService;
	private final CategoryRepository categoryRepository;
	private final ObjectMapper objectMapper;

	public CategoryController(UserRepository userRepository, CategoryService categoryService,
			CategoryRepository categoryRepository, ObjectMapper objectMapper) {
		this.userRepository = userRepository;
		this.categoryService = categoryService;
		this.categoryRepository = categoryRepository;
		this.objectMapper = objectMapper;
	}

	// カテゴリ一覧画面の表示
	@GetMapping("/categories")
	public String showCategoryList(Model model) {

		List<Category> categories;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		Optional<Users> userOpt = userRepository.findByEmail(email);
		if (userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());

			categories = categoryService.findAll();
			// JSON文字列に変換してモデルに追加
			try {
				model.addAttribute("categoriesJson", objectMapper.writeValueAsString(categories));
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}

			return "category/list";

		} else {
			// ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
	}

	// カテゴリ登録画面の表示
	@GetMapping("/categorycreate")
	public String showCategoryCreatePage(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		Optional<Users> userOpt = userRepository.findByEmail(email);
		if (userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
			return "category/create";
		} else {
			// ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
	}

	// カテゴリ登録処理
	@PostMapping("/categorycreate")
	public String categoryCreate(@Valid CategoryCreateDTO dto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "category/create";
		}

		// 重複チェック
		if (categoryService.isDuplicate(dto.getCategoryName())) {
			model.addAttribute("error", "このカテゴリは既に存在します。");
			return "category/create";
		}

		// 問題がなければカテゴリを保存
		categoryService.saveCategory(dto);
		return "redirect:/categories";
	}

	// カテゴリ編集画面の表示
	@GetMapping("/categoryedit/{id}")
	public String categoryEdit(@PathVariable Long id, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if (userOpt.isPresent()) {
			Optional<Category> optionalCategory = categoryRepository.findById(id);
				if (optionalCategory.isPresent()) {
					Category category = optionalCategory.get();
					Users user = userOpt.get();
					model.addAttribute("currentUsername", user.getUsername());
					model.addAttribute("category", category);
					return "category/edit";
				}
				return "error/404";		
		} else {
			// ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
	}


	// カテゴリの編集処理
	@PostMapping("/categoryedit")
	public String categoryEdit(@Valid CategoryUpdateDTO dto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("category", dto);
			return "category/edit";
		}

		// 重複チェック
		if (categoryService.isDuplicateIdNot(dto.getCategoryName(), dto.getId())) {
			model.addAttribute("error", "このカテゴリは既に存在します。");
			model.addAttribute("category", dto);
			return "category/edit";
		}

		// 問題なければカテゴリを更新
		categoryService.updateCategory(dto.getId(), dto);
		return "redirect:/categories";
	}

	// カテゴリ削除処理
	@DeleteMapping("/categorydelete/{id}")
	public String categoryDelete(@PathVariable Long id) {

		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			categoryService.deleteCategory(category);
		}
		return "error/404";
	}
}

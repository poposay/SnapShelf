package com.say.popo.snapshelf.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import com.say.popo.snapshelf.entity.Users;
import com.say.popo.snapshelf.repository.UserRepository;

@Controller
public class ProductConfirmationController {
	
	private final UserRepository userRepository;
	
	public ProductConfirmationController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/productconfirmation")
	public String showProductConfirmationPage(Model model) {

		//今日の日付を取得
		LocalDate today = LocalDate.now();
		System.out.println("取得した日付：" + today);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M月d日");
		String todayString = today.format(formatter);
		System.out.println("フォーマットした日付：" + todayString);
		model.addAttribute("memoryDate",todayString);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		Optional<Users> userOpt = userRepository.findByEmail(email);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			model.addAttribute("currentUsername", user.getUsername());
			return "productconfirmation";
		}else {
			//ユーザーが見つからなかった場合
			return "redirect:/error/unauthorized";
		}
	}
}

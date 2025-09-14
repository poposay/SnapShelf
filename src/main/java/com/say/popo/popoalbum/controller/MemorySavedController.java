package com.say.popo.popoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

@Controller
public class MemorySavedController {

	@GetMapping("/memorysaved")
	public String showMemorySavedPage(Model model) {
		System.out.println("memorySaved呼び出し");
		//今日の日付を取得
		LocalDate today = LocalDate.now();
		System.out.println("取得した日付：" + today);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M月d日");
		String todayString = today.format(formatter);
		System.out.println("フォーマットした日付：" + todayString);
		model.addAttribute("memoryDate",todayString);
		
		System.out.println("modelに渡されたpopoMessage：" + model.getAttribute("popoMessage"));
		return "memorysaved";
	}
}

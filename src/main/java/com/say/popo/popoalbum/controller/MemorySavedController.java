package com.say.popo.popoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemorySavedController {

	@GetMapping("/memorysaved")
	public String showMemorySavedPage() {
		return "memorysaved";
	}
}

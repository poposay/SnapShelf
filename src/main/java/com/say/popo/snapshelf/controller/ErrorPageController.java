package com.say.popo.snapshelf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {
	
	@GetMapping("/error/unauthorized")
	public String unauthorized() {
		return "error/unauthorized";
	}

}

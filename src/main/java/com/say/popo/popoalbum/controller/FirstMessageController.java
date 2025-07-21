package com.say.popo.popoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.say.popo.popoalbum.service.FirstMessageService;

@Controller
public class FirstMessageController {
	
	private final FirstMessageService firstMessageService = new FirstMessageService();
	
	@GetMapping("/firstmessage")
	public String showFirstMessagePage() {
	    return "firstmessage";
	}
	
	@PostMapping("/firstmessage")
	public String generateFirstMessage(@RequestParam String imagePath,
	                                   @RequestParam String userComment,
	                                   Model model) {
	    String popoMessage = firstMessageService.generateMessage(imagePath, userComment);
	    model.addAttribute("popoMessage", popoMessage);
	    return "firstmessage";
	}
	

}

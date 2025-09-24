package com.say.popo.snapshelf.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.say.popo.snapshelf.entity.Users;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	@ModelAttribute("currentUser")
	public Users getCurrentUser(HttpSession session) {
		return (Users) session.getAttribute("currentUser");
	}
	
	@ModelAttribute("userId")
	public Long getUserId(HttpSession session) {
		return (Long) session.getAttribute("userId");
	}

}

package com.say.popo.popoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlbumController {
	
	@GetMapping("/album")
	public String showAlbumPage() {
		return "album";
	}

}

/*
package com.say.popo.snapshelf.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	
	public static final String UPLOAD_DIR = "src/main/resources/static/uploads/";
	private static final int MAX_WIDTH = 800;
	private static final int MAX_HEIGHT = 800;
	private static final double Quality = 0.8;
	
	public String saveAndCompressImage(MultipartFile file) throws IOException {
		
		//ファイル名生成
	      String originalFilename = file.getOriginalFilename();
	      String extension = getExtension(originalFilename);
	      String filename = UUID.randomUUID() + "_" + System.currentTimeMillis() + extension;
		
	}

}

*/
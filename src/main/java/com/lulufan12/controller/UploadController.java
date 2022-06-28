package com.lulufan12.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lulufan12.service.StorageService;

@RestController
public class UploadController {
	
	private final StorageService service;
	
	public UploadController(StorageService service) {
		this.service = service;
	}
	
	@PostMapping
	public void upload(@RequestParam MultipartFile file) {
		service.save(file);
	}
}

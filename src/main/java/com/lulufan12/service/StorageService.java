package com.lulufan12.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;

@Service
public class StorageService {
	
	private final MinioClient minioClient;
	
	StorageService(MinioClient minioClient) {
		this.minioClient = minioClient;
	}
	
	public void save(MultipartFile multipartFile) {
		try {
			
			File file = convertMultipartFileToFile(multipartFile);
			UploadObjectArgs args = uploadArgs(multipartFile, file);
			minioClient.uploadObject(args);
			
			System.out.println("Success!");
		} catch (Exception ex) {
			System.err.println("ERROR: " + ex.getMessage());
		}
	}

	private UploadObjectArgs uploadArgs(MultipartFile multipartFile, File file) throws IOException {
		UploadObjectArgs args = UploadObjectArgs.builder()
				.bucket("booking") // choose your bucketname
				.filename(file.getName())
				.object(file.getAbsolutePath())
				.build();
		return args;
	}
	
	private File convertMultipartFileToFile(MultipartFile multipart) throws IOException {
		File file = new File(multipart.getOriginalFilename());
		
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(multipart.getBytes());
		fos.close();
			
		return file;
	}
}

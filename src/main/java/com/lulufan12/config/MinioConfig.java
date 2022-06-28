package com.lulufan12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfig {
	
	@Bean
	public MinioClient minioClient() {
		return MinioClient.builder()
				.credentials("my_access_key", "my_secret_key")
				.endpoint("http://localhost:9000")
				.build();
	}
}

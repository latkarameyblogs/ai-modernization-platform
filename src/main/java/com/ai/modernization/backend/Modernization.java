package com.ai.modernization.backend;

import com.ai.modernization.backend.config.ModernizationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ModernizationProperties.class)
public class Modernization {

	public static void main(String[] args) {
		SpringApplication.run(Modernization.class, args);
	}

}

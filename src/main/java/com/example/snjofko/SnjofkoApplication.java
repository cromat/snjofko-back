package com.example.snjofko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SnjofkoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnjofkoApplication.class, args);
	}

}

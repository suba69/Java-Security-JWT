package com.example.security1337;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.security1337.mapper")
public class Security1337Application {

	public static void main(String[] args) {
		SpringApplication.run(Security1337Application.class, args);
	}

}

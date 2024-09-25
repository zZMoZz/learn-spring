package com.example.spring_core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {"com.example.spring_core",
							"com.example.outside"}) // now spring can collect beans in this further package
public class SpringCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringCoreApplication.class, args);
	}
}

package com.lugiatracker.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LugiaTrackerApplication {

	public static void main(String[] args) {
		com.lugiatracker.config.DotenvConfig.loadEnv();
		SpringApplication.run(LugiaTrackerApplication.class, args);
	}

}

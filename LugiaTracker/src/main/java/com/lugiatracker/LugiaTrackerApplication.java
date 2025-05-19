package com.lugiatracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
@ComponentScan
@EntityScan
@SpringBootApplication
public class LugiaTrackerApplication {

	public static void main(String[] args) {
		//com.lugiatracker.config.DotenvConfig.loadEnv();
		SpringApplication.run(LugiaTrackerApplication.class, args);
	}

}

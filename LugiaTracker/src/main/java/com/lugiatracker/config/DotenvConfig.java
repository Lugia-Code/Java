package com.lugiatracker.config;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvConfig {
	public static void loadEnv() {
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()
				.load();
		
		
		dotenv.entries().forEach(entry -> 
			System.setProperty(entry.getKey(),entry.getValue())
				);
	}
}

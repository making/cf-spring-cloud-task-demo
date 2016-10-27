package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
public class CfSpringCloudTaskDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfSpringCloudTaskDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return a -> {
			System.out.println("Hello World!");
		};
	}
}

package com.project.quizletclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.project.quizletclone.repository")
public class QuizletCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizletCloneApplication.class, args);
	}
}

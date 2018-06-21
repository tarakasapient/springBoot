package com.exercise.springbootexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication/*(scanBasePackages={"com.exercise"})
@EnableJpaRepositories("repository")*/
public class SpringBootExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExerciseApplication.class, args);
	}
}

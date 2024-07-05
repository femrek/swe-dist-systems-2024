package com.swedist.computer_lab_gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.swedist.computer_lab_gradle")
public class ComputerLabGradleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ComputerLabGradleApplication.class, args);
	}
}

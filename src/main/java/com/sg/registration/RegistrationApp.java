package com.sg.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.sg.registration"})
public class RegistrationApp {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApp.class, args);
	}
}

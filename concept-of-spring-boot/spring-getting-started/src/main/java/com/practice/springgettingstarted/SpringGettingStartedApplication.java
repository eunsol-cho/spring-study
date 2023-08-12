package com.practice.springgettingstarted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringGettingStartedApplication {
	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);
		SpringApplication application = new SpringApplication(SpringGettingStartedApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE); // 웹애플리케이션 아닌 애플리케이션으로 구동
		application.run(SpringGettingStartedApplication.class, args);
	}
}


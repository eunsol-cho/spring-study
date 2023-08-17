package com.practice.springgettingstarted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);
		SpringApplication application = new SpringApplication(Application.class);
		//application.setWebApplicationType(WebApplicationType.NONE); // 웹애플리케이션 아닌 애플리케이션으로 구동
		application.run(args);
	}

	/*@Bean
	public Holoman holoman() {
		Holoman holoman = new Holoman();
		holoman.setName("new esjo");
		holoman.setHowLong(10);
		return holoman;
	}*/

	/*
	 * 위의 빈은 무시되고, autoconfigure에서 정의된 값이 나온다.
	 * 빈생성의 순서가 [1]componentScane -> [2]autoconfigure 순이므로, 오버라이드됨.
	 */
}


package com.practice.usespringboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunnerWithProperties implements ApplicationRunner {

	@Value("${esjo.name}")
	private String name;

	@Value("${esjo.age}")
	private String age;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("=====================");
		System.out.println(name);
		System.out.println(age);
		System.out.println("=====================");
	}
}

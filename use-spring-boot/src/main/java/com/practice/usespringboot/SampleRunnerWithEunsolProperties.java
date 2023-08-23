package com.practice.usespringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunnerWithEunsolProperties implements ApplicationRunner {

	@Autowired
	EunsolProperties eunsolProperties;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("=====================");
		System.out.println("eunsolProperties : " + eunsolProperties.getName());
		System.out.println("eunsolProperties : " + eunsolProperties.getAge());
		System.out.println("eunsolProperties : " + eunsolProperties.getSessionTimeout());
		System.out.println("=====================");
	}
}

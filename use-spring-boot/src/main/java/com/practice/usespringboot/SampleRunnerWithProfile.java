package com.practice.usespringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunnerWithProfile implements ApplicationRunner {

	@Autowired
	private String hello;

	@Autowired
	private EunsolProperties eunsolProperties;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("=====================");
		System.out.println("SampleRunnerWithProfile : " + hello);
		System.out.println("SampleRunnerWithProfile : " + eunsolProperties.getName());
		System.out.println("SampleRunnerWithProfile : " + eunsolProperties.getAge());
		System.out.println("=====================");
	}
}

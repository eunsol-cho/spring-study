package com.practice.usespringboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class ArgsPrint {
	// [!] 어떤 bean에 생성자가 하나이고, 파라미터가 bean일 경우 Spring이 알아서 주입을 해줌
	public ArgsPrint(ApplicationArguments arguments){
		System.out.println("foo : " + arguments.containsOption("foo"));
		System.out.println("bar : " + arguments.containsOption("bar"));
	}
}

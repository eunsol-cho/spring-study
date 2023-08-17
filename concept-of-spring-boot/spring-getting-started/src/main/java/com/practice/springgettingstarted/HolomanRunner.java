package com.practice.springgettingstarted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.practice.Holoman;

@Component
public class HolomanRunner implements ApplicationRunner {
// ApplicationRunner : 스프링부트 어플리케이션이 떴을때, arg인자를 받아서 자동으로 실행되는 빈을 만들고 싶을때

	@Autowired
	Holoman holoman;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(holoman);
	}
}

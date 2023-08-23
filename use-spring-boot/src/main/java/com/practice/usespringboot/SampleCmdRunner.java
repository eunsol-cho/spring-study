package com.practice.usespringboot;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleCmdRunner implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		Arrays.stream(args).forEach(System.out::println);
	}
}

package com.practice.usespringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunnerWithLogger implements ApplicationRunner {
	private Logger logger = LoggerFactory.getLogger(SampleRunner.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("log ::: info");
		logger.debug("log ::: debug");

		// sl4j의 구현체 중 logback을 쓰는지 log4j를 쓰는지 확인
		String loggerImpl = StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr();
		System.out.println("Currently bound logging framework: " + loggerImpl);
	}
}

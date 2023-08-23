package com.practice.usespringboot;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//@Component -> ApplicationStartingEvent 는 applicationContext생성 이전에 발생하므로 의미가 없다.
//public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {
@Component
public class SampleListener implements ApplicationListener<ApplicationStartedEvent> {
	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		System.out.println("=======================");
		System.out.println("Application is starting");
		System.out.println("=======================");
	}
}

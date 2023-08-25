package com.practice.usespringboot;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("esjo")
public class EunsolProperties {

	String name;
	int age;
	String fullName;

	// 값을 초로 받겠다. 디폴트값은 30초
	//@DurationUnit(ChronoUnit.SECONDS)  // 해당 어노테이션을 써도 되고, s를 붙여서 데이터 형태로 자동 컨버전 가능
	Duration sessionTimeout = Duration.ofSeconds(30);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Duration getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(Duration sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
}

package com.practice.usespringboot;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

// 1
//@TestPropertySource(properties = "esjo.name=eunsol3")
// 2
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "esjo.name=eunsol2")
// 3 : 파일로 관리 가능 -> @SpringBootTest properties 속성보단 후순위
//@TestPropertySource(locations = "classpath:/test.properties")
class ApplicationTests {

	@Autowired
	Environment environment; // 프로퍼티값들을 가진 객체

	@Test
	void contextLoads() {
		assertThat(environment.getProperty("esjo.name"))
				.isEqualTo("eunsol2");
	}

}

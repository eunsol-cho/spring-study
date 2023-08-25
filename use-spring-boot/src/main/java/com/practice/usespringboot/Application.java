package com.practice.usespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(EunsolProperties.class)
public class Application {

	// SpringApplication 실행방법 3가지
	/*public static void main(String[] args) {
		// [1] deafault
		//SpringApplication.run(Application.class, args);

		// [2] instance
		//SpringApplication app = newSpringApplication(Application.class);
		//app.run(args); // 기본 로그레벨 : INFO

		// [3] SpringApplicationBuilder
		new SpringApplicationBuilder()
				.sources(Application.class)
				.run(args);
	}*/


	// 코드로 banner 설정. resource아래 banner.txt 파일이 우선순위 더 높음
	/*public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setBanner((enviroment, sourceClass, out) -> {
			out.println("=============");
			out.println("esjo Banner");
			out.println("=============");
		}); // 코드에 배너 설정
		app.setBannerMode(Banner.Mode.OFF); // 배너끄기
		app.run(args);
	}*/

	// ApplicationListener
	/*public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		//app.addListeners(new SampleListener()); 빈으로 등록된 이벤트리스너의 경우 직접 세팅 안해줘도 된다.
		app.run(args);
	}*/

	// WebApplicationType
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		// classpath에 servlet이 있음에도, REACTIVE하고 싶을 경우 직접 설정.
		//app.setWebApplicationType(WebApplicationType.REACTIVE);
		app.run(args);
	}

}

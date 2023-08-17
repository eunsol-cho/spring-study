package com.practice.springgettingstarted;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Application2 {

	public static void main(String[] args) throws LifecycleException {
		// tomcat 객체 생성
		Tomcat tomcat = new Tomcat();
		// port설정
		tomcat.setPort(8080);
		tomcat.getConnector(); // tomcat 9 버전부터는 getConnector() 를 자동으로 추가해주지 않는다고 하네요.
		// tomcat에 context추가
		Context context = tomcat.addContext("/a", "/");

		// servlet생성
		HttpServlet servlet = new HttpServlet() {
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				PrintWriter writer = resp.getWriter();
				writer.println("<html><head><tiltle>");
				writer.println("Hey, Tomcat");
				writer.println("</tiltle></head>");
				writer.println("<body><h1>Hello Tomcat</h1></body>");
				writer.println("</html>");
			}
		};

		// tomcat에 servlet등록
		String serveletName = "helloServlet";
		tomcat.addServlet("/a", serveletName, servlet);
		// context에 servlet매핑
		context.addServletMappingDecoded("/hello", serveletName); // hello요청이 오면, 해당 servletName에 해당하는 servlet을 실행
		// tomcat실행
		tomcat.start();
		//tomcat.getServer().await();
	}

}


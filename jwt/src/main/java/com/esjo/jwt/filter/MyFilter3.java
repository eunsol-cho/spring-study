package com.esjo.jwt.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter3 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 토큰 : esjo
        // 로그인 인증이 끝나면, 토큰을 발급
        // 이후 요청시 토큰을 가져옴 (in header의 Authorization 값으로)
        // 그때, 토큰이 넘어오면, 내가 만든 토근인지를 검증 (RSA, HS256)
        if (req.getMethod().equals("POST")) {
            String headerAuth = req.getHeader("Authorization");
            System.out.println("headerAuth = " + headerAuth); // 한글 안들어감

            if ("esjo".equals(headerAuth)) {
                chain.doFilter(req, res);
            } else {
                PrintWriter out = res.getWriter();
                out.println("인증안됨");
            }
        }

        System.out.println("필터3");
        //chain.doFilter(request, response); // 체인을 이어줘야지 프로세스가 이어짐
    }
}

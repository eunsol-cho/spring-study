package com.esjo.basicsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity // 웹보안이 활성화됨
@Order(0)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}111").roles("USER");
        auth.inMemoryAuthentication().withUser("sys").password("{noop}111").roles("SYS");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}111").roles("ADMIN", "SYS", "USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
                .authorizeRequests()
                .anyRequest().authenticated();*/

        /**
         * form login configure
         * */
        http.formLogin()
                // 사용자 정의 로그인 페이지
                //.loginPage("/loginPage") : 기본 제공 로그인 페이지 사용
                // 로그인 성공 후 이동 페이지
                .defaultSuccessUrl("/")
                // 로그인 실패 후 이동 페이지
                .failureUrl("/loginPage")
                // 아이디 파라미터명 설정
                .usernameParameter("userId")
                // 패스워드 파라미터명 설정
                .passwordParameter("passwd")
                // 로그인 Form Action Url
                .loginProcessingUrl("/login_proc")
                // 로그인 성공 후 핸들러
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        System.out.println("authentication : " + authentication.getName());

                        RequestCache requestCache = new HttpSessionRequestCache();
                        SavedRequest savedRequest = requestCache.getRequest(request, response);
                        String redirectUrl = savedRequest.getRedirectUrl();
                        response.sendRedirect(redirectUrl);
                    }
                })
                // 로그인 실패 후 핸들러
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        System.out.println("exception : " + exception.getMessage());
                        response.sendRedirect("/login");

                    }
                })
                .permitAll(); // loginPage 접근 허용

        /**
         * logout configure
         * */
        http.logout() // 기본적으로 post만 지원함
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                // 커스텀 로그아웃 처리가능
                .addLogoutHandler((request, response, authentication) -> {
                    HttpSession session = request.getSession();
                    session.invalidate();
                })
                // logoutSuccessUrl 단순히 url 이동만, logoutSuccessHandler 로직 추가 가능
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.sendRedirect("/login");
                })
                .deleteCookies("remember-me"); // 로그아웃 시 쿠키 삭제


        /**
         * remember me configure
         */
        http.rememberMe()
                .rememberMeParameter("remember") // 기본 파라미터면은 remember-me : form태그에서 같은 이름으로 넘겨줘야함
                .tokenValiditySeconds(3600) // 토큰유지 1시간 (default = 14일)
                .alwaysRemember(true) // 리멤버 미 기능이 활성화 되지 않아도 항상 실행 (default = fasle)
                .userDetailsService(userDetailsService); // 리멤버 미 기능이 동작할 때 사용자 정보를 얻어오는 서비스


        /**
         * session management configure
         */

        // 동시 세션 제어
        http.sessionManagement()
                .maximumSessions(1) // 최대 허용 가능 세션 수, -1 : 무제한 로그인 세션 허용
                .maxSessionsPreventsLogin(true) // 동시 로그인 차단함, default = false
                .expiredUrl("/expired"); // 세션이 만료된 경우 이동할 페이지

        // 세션 고정 보호
        http.sessionManagement()
                .sessionFixation().changeSessionId();

        // 세션 정책
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        /**
         * 권한 설정
         * ** 설정 시 구체적인 경로가 먼저 오고 그것 보다 큰 범위의 경로가 뒤에 오도록 해야한다.
         */
        http
            .authorizeRequests()
                .antMatchers("/login").permitAll() // login 페이지는 누구나 접근 가능
                .antMatchers("/user").hasRole("USER") // USER 권한만 접근 가능
                .antMatchers("/admin/pay").hasRole("ADMIN") // ADMIN 권한만 접근 가능
                .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')") // ADMIN, SYS 권한만 접근 가능
                .anyRequest().authenticated(); // 나머지 요청은 인증만 되면 접근 가능

        /**
         * 인가 예외 처리
         */
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.sendRedirect("/login");
                    }
                }) // 인증 예외 처리
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.sendRedirect("/denied");
                    }
                }); // 인가 예외 처리


    }
}
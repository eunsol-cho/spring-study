package com.esjo.jwt.config;

import com.esjo.jwt.config.jwt.JwtAuthenticationFilter;
import com.esjo.jwt.config.jwt.JwtAuthorizationFilter;
import com.esjo.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsConfig corsConfig;
    private final UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.addFilter(new MyFilter1()) // 시큐리티 필터만 등록 가능해, addFilterAfter/Before 로는 걸 수 있다.
                //.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class) // 이렇게 넣을수 있는데, 그럼 시큐리티 필터 순서를 알아야한다.
                // 하지만 위의 방식처럼 굳이 시큐리티 필터에 필터를 넣을 필요는 없다. (FilterConfig.java 파일 참조)
                //.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class) // 가장먼저 실행됨

                .addFilter(corsConfig.corsFilter()) // CorsFilter 필터 추가 - cors이 모두 허용
                // (컨트롤러에 @CrossOrigin 이거 다는건, 인증 없는 것만 처리. 이 방식이 인증 있는것도 처리해주는 방법)
                .csrf().disable() // csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않고, stateless서버로 사용
                .and()
                .formLogin().disable() // jwt 사용하니까 form 로그인 안함 (form태그 만들어서 로그인하는거 안한다)
                .httpBasic().disable() // 기본적인 로그인 방식안씀
                // 요청에 따른 세션을 서버에서 만들어서 리턴 하는 방식
                // 문제점1. 서버가 여러대인 경우 요청처리
                // 문제점2. 쿠키는 동일 도메인에서의 요청만 처리 가능 (서버의 도메인과 같아야한다. js상에 쿠키를 어거지로 담아 보낼 수 있지만, 서버측에서 httpOnly로 해서 막는다)
                // header authorization에 ID/PW담아 - "http basic 방식" => 암호화 안됨 -> https로 암호화
                // header authorization에 토큰담아 - 노출이 되더라도 위험부담이 적다. - "http bearer 방식"
                // 이때, 토큰을 JWT방식으로 담는거임.
                // 여기까지는 고정 옵션 - jwt를 사용하기 위한 고정 옵션

                // /login 경로 설정
                // 인증 - 로그인
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager <- WebSecurityConfigurerAdapter가 가지고 있다.
                // 인가 - 권한확인
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))

                // 권한이 필요한 주소
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")// 해당주소로 들어오면, 아래와 같은 롤체크를 한다.
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll() // 위에 해당하지 않는 요청은 모두 허용


        ;
    }
}
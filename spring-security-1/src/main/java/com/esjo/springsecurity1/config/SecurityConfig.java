package com.esjo.springsecurity1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨. 필터 체인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 빈 어노테이션 : 해당 메서드의 리턴값을 IoC에 등록해줌
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() // 해당 url패턴은 인증이 필요
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                // 권한이 없을때, 403페이지가 아닌 아래 설정 경로로 이동
                .and()
                .formLogin()
                .loginPage("/loginForm")
                // 해당 주소가 호출이 되면, 시큐리티가 낚아채서 대신 로그인을 진행 => 컨트롤러에 login 진입점 안만들어도됨
                .loginProcessingUrl("/login")
                //.usernameParameter() -> username 파라미터명 변경 옵션, 그냥 그대로 쓰는거 ㅊㅊ
                .defaultSuccessUrl("/");
    }
}
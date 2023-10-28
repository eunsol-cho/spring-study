package com.esjo.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내 서버가 응답시에 json 을 자바스크립트에서 처리할 수 있게 설정
        config.addAllowedOrigin("*"); // 모든 ip 응답을 허용. e.g. http://domain1.com
        config.addAllowedHeader("*"); // 모든 header 응답을 허용.
        config.addAllowedMethod("*"); // 모든 post,get,put,delete,patch 응답을 허용.

        source.registerCorsConfiguration("/api/**", config); // 해당 패턴은 모두 이 컨피그를 따름
        return new CorsFilter(source);
    }

}
package com.esjo.jwt.config;

import com.esjo.jwt.filter.MyFilter1;
import com.esjo.jwt.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    // 시큐리티 필터가 모두 실행되고, 실행된다.
    // 시큐리티 필터 >> 커스텀 필터
    // 만약, 시큐리티 필터보다 먼저 실행되고 싶으면, 시큐리티 필터의 addFilterBefore으로 등록해서 사용해야한다.
    // SecurityContextPersistenceFilter.class(시큐리티 필터중 가장먼저 실행되는 필터) 보다 우선 실행하게 등록 필요.
    @Bean
    public FilterRegistrationBean<MyFilter1> filter1() {
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 가장 낮은 우선 순위의 필터로 걸림
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter2> filter2() {
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }

}

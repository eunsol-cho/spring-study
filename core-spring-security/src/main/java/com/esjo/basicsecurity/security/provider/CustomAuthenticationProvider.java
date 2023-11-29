package com.esjo.basicsecurity.security.provider;

import com.esjo.basicsecurity.security.common.FormWebAuthenticationDetails;
import com.esjo.basicsecurity.security.service.AccountContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 사용자의 입력 정보 추출
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        // DB에서 사용자 정보 조회
        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, accountContext.getAccount().getPassword())) {
            // 인증 실패
            throw new BadCredentialsException("BadCredentialsException");

        }

        // secret key 를 추가 인증 정보로 활용
        FormWebAuthenticationDetails formWebAuthenticationDetails = (FormWebAuthenticationDetails) authentication.getDetails();
        String secretKey = formWebAuthenticationDetails.getSecretKey();
        if (secretKey == null || !"secret".equals(secretKey)) {
            throw new InsufficientAuthenticationException("InsufficientAuthenticationException");
        }

        // 인증객체 생성
        var authenticationToken = new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());

        // 인증 처리후, 인증 객체 리턴
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // authentication 클래스 타입과 CustomAuthenticationProvider 클래스가 사용하고자 하는 토큰 타입과 일치할 때 인증을 처리
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication); // form로그인 토큰 타입
    }

}

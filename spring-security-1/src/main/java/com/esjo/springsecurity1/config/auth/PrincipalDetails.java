package com.esjo.springsecurity1.config.auth;

import com.esjo.springsecurity1.model.Users;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 시큐리티가 /login 낚아채서 로그인을 진행
// 로그인 완료시 session을 만들어줌 -> 시큐리티만의 세션 공간이 있다. (Security ContextHolder)
// Security ContextHolder 에 저장될 수 있는 객체 => Authentication
// Authentication 안에 User정보 필요
// User오브젝트 타입 => UserDetails 타입객체

// Security session => Authentication => UserDetails

// Authentication 객체에 저장할 수 있는 유일한 타입
@Data
public class PrincipalDetails implements UserDetails {

    private Users user;

    public PrincipalDetails(Users user) {
        super();
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 1년 동안 로그인 안하면, 휴면
        // 이런 로직들 처리
        return true;
    }

    // 해당 유저의 권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 스트링 타입을 변환
        Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
        collet.add(()->{ return user.getRole();});
        return collet;
    }

}
package com.esjo.basicsecurity.security.service;

import com.esjo.basicsecurity.domain.Account;
import com.esjo.basicsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = userRepository.findByUsername(username);

        if(account == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        // 권한정보
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(account.getRole()));

        // spring security에서 UserDetails를 구현한 User객체를 제공
        // AccountContext <- User <- UserDetails
        AccountContext accountContext = new AccountContext(account, roles);

        return accountContext;
    }
}

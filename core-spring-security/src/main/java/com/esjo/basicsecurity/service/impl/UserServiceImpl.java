package com.esjo.basicsecurity.service.impl;

import com.esjo.basicsecurity.domain.Account;
import com.esjo.basicsecurity.repository.UserRepository;
import com.esjo.basicsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Account account) {

        userRepository.save(account);

    }
}

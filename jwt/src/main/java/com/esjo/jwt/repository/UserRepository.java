package com.esjo.jwt.repository;

import com.esjo.jwt.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 를 상속하면 자동 컴포넌트 스캔됨.
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);

}
package com.esjo.springsecurity1.controller;

import com.esjo.springsecurity1.model.Users;
import com.esjo.springsecurity1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // view return
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping({ "/user"})
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping({ "/admin"})
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping({ "/manager"})
    public @ResponseBody String manager() {
        return "manager";
    }

    @GetMapping({ "/login"})
    public String login() {
        return "loginForm";
    }

    @GetMapping({ "/joinForm"})
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(Users user) {
        System.out.println("회원가입 진행 : " + user);
        String rawPassword = user.getPassword();
        // 패스워드 암호화가 안되면, 시큐리티로 로그인 할수 없다. 그래서 암호화 처리 해야함
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "redirect:/";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping({ "/info"})
    public @ResponseBody String info() {
        return "info";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping({ "/data"})
    public @ResponseBody String data() {
        return "data";
    }

}

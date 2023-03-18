package com.kny.exam.jpaBoard.user.controller;

import com.kny.exam.jpaBoard.user.dao.UserRepository;
import com.kny.exam.jpaBoard.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;


@Controller
@RequestMapping("/usr/user")

public class UserController {
    @Autowired
    private UserRepository userRepository;



    @RequestMapping("doLogin")
    @ResponseBody
    public String doLogin(String email, String password) {


        if (email == null || email.trim().length() == 0) {
            return "이메일을 입력해주세요.";
        }
        email = email.trim();

        User user = userRepository.findByEmail(email).get();

        if ( user == null ) {
            return "회원이 존재 하지 않습니다.";
        }


        System.out.println("user.getPassword() : " + user.getPassword());
        System.out.println("password : " + password);

        if ( user.getPassword().equals(password) == false ) {
            return "패스워드가 틀립니다.";
        }

        return "%s 님 환영합니다.".formatted(user.getName());
    }

    @RequestMapping("doJoin")
    @ResponseBody
    public String doJoin(String name, String email, String password) {


        if( name == null || name.trim().length() == 0) {
            return "이름을 입력해주세요.";
        }
        name = name.trim();

        if( email == null || email.trim().length() == 0) {
            return "이메일을 입력해주세요.";
        }
        email = email.trim();

        boolean existsByEmail = userRepository.existsByEmail(email);

        if (existsByEmail) {
            return "입력 하신 %s은 이미 사용중인 이메일 입니다".formatted(email);
        }


        if( password == null || password.trim().length() == 0) {
            return "패스워드를 입력해주세요.";
        }
        password = password.trim();

        User user = new User();
        user.setRegDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        return "%d번 회원이 가입 되었습니다.".formatted(user.getId());

    }


    }


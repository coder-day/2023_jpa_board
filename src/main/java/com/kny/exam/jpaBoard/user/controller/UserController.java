package com.kny.exam.jpaBoard.user.controller;

import com.kny.exam.jpaBoard.user.dao.UserRepository;
import com.kny.exam.jpaBoard.user.domain.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Optional;


@Controller
@RequestMapping("/usr/user")

public class UserController {
    @Autowired
    private UserRepository userRepository;



    @RequestMapping("doLogin")
    @ResponseBody
    public String doLogin(String email, String password, HttpServletRequest req, HttpServletResponse resp) {

        if (email == null || email.trim().length() == 0) {
            return "이메일을 입력해주세요.";
        }
        email = email.trim();

        //User user = userRepository.findByEmail(email).orElse(null);// 첫번째 방법
        Optional<User> user = userRepository.findByEmail(email);
        if ( user.isEmpty() ) {
            return "회원이 존재 하지 않습니다.";
        }


        System.out.println("user.getPassword() : " + user.get().getPassword());
        System.out.println("password : " + password);

        if ( user.get().getPassword().equals(password) == false ) {
            return "패스워드가 틀립니다.";
        }

        Cookie cookie = new Cookie("loginedUserId", user.get().getId() + "");
        resp.addCookie(cookie);

        return "%s 님 환영합니다.".formatted(user.get().getName());
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

    @RequestMapping("me")
    @ResponseBody
    public User showMe(HttpServletRequest req) {
        boolean isLogined = false;
        long loginedUserId = 0;

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginedUserId")) {
                    isLogined = true;
                    loginedUserId = Long.parseLong(cookie.getValue());
                }
            }
        }

        if (isLogined == false) {
            return null;
        }

        Optional<User> user = userRepository.findById(loginedUserId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

}


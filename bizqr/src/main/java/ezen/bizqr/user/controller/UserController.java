package ezen.bizqr.user.controller;

import ezen.bizqr.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/*")
@Controller
public class UserController {

    private final UserService msv;

    @GetMapping("/register")
    public String register(){

        return "/user/register";
    }

    @GetMapping("/login")
    public String login(){

        return "/user/login";
    }
}

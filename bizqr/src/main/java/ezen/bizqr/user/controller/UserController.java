package ezen.bizqr.user.controller;

import ezen.bizqr.user.security.UserVO;
import ezen.bizqr.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/*")
@Controller
public class UserController {

    private final UserService usv;

    @GetMapping("/register")
    public String register(){

        return "/user/register";
    }

    @PostMapping("/register")
    public String register(UserVO uvo){
        log.info(">>> uvo >>> {} ", uvo);

        int isOk = usv.userRegister(uvo);

        return "index";
    }

    @GetMapping("/login")
    public String login(){

        return "/user/login";
    }

    @GetMapping(value="/checkEmail/{email}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String checkEmail(@PathVariable("email") String email){
        log.info(">>> check email >>> " + email);

        UserVO uvo = usv.checkEmail(email);
        return uvo != null ? "0" : "1";
    }

}

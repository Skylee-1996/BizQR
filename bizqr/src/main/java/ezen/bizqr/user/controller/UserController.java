package ezen.bizqr.user.controller;

import ezen.bizqr.user.security.UserVO;
import ezen.bizqr.user.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/*")
@Controller
public class UserController {

    private final UserService usv;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(){

        return "/user/register";
    }

    @PostMapping("/register")
    public String register(UserVO uvo){
        log.info(">>> uvo >>> {} ", uvo);
        uvo.setPwd(passwordEncoder.encode(uvo.getPwd()));

        int isOk = usv.userRegister(uvo);

        return "index";
    }

    @GetMapping("/login")
    public String login(){

        return "/user/login";
    }

    @GetMapping("/modify")
    public String modify(){

        return "/user/modify";
    }

    @GetMapping(value="/checkEmail/{email}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String selectEmail(@PathVariable("email") String email){
        log.info(">>> check email >>> " + email);

        UserVO uvo = usv.selectEmail(email);
        return uvo != null ? "0" : "1";
    }

    private void logout(HttpServletRequest req, HttpServletResponse res){
         Authentication authentication =
                 SecurityContextHolder.getContext().getAuthentication();
         new SecurityContextLogoutHandler().logout(req, res, authentication);
    }

}

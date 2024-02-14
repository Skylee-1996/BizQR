//package ezen.bizqr.user.controller;
//
//import ezen.bizqr.user.service.OAuthServiceImpl;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//@RequestMapping(value = "/login/oauth2", produces = "application/json")
//public class OAuthController {
//
//    private final PasswordEncoder passwordEncoder;
//    private final OAuthServiceImpl oAuthServiceImpl;
//
//    @GetMapping("/code/{registrationId}")
//    public String googleLogin(@RequestParam String code, @PathVariable String registrationId ){
//        log.info("테스트");
//        oAuthServiceImpl.socialLogin(code, registrationId);
//
//        return "/index";
//    }
//
//}

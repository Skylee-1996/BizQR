package ezen.bizqr.user.controller;

import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.board.handler.PagingHandler;
import ezen.bizqr.user.security.OAuthVO;
import ezen.bizqr.user.security.UserVO;
import ezen.bizqr.user.service.CustomOAuth2UserService;
import ezen.bizqr.user.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/*")
@Controller
public class UserController {

    private final UserService usv;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/test/login")
    public @ResponseBody String loginTest(Authentication authentication, @AuthenticationPrincipal OAuth2User oauth){
        log.info("/test/login ==============>");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        log.info("authentication : " + oAuth2User.getAttributes());
        log.info("oauth2User : " + oauth.getAttributes());
        return "OAuth 세션 정보 확인하기";
    }

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

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }

    @GetMapping("/modify")
    public String modify(Model m){
        //현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        log.info(">>> userEmail >>> : " + userEmail);

        UserVO uvo = usv.selectEmail(userEmail);
        log.info(">>> uvo >>> {}", uvo);

        m.addAttribute("UserVO", uvo);

        return "/user/modify";
    }

    @PostMapping("/modify")
    public String modify(UserVO uvo){

        log.info(">>> uvo >>> {}", uvo);
        uvo.setPwd(passwordEncoder.encode(uvo.getPwd()));

        int isOk = usv.userModify(uvo);
        log.info(">>> user Modify >>> " + ((isOk == 1) ? "OK" : "Fail"));

        UserVO socialUserVO = usv.selectEmail(uvo.getEmail());
        log.info(">>> socialUserVO >>> {}", socialUserVO);
        if(socialUserVO.getIsSocial() == 1){
            OAuthVO oAuthVO = usv.selectSocialUserDomain(socialUserVO.getEmail());
            oAuthVO.setNickName(uvo.getNickName());
            log.info(">>> oAuthVO >>> {}", oAuthVO);
            isOk = usv.socialUserModify(oAuthVO);
            log.info(">>> Social User Modify >>> " + ((isOk == 1) ? "OK" : "Fail"));
        }


        return  "/index";
    }

    @GetMapping("/list")
    public String list(Model m, PagingVO pgvo){

        List<UserVO> list = usv.getUserList(pgvo);

        int totalCount = usv.getTotalUserCount(pgvo);

        PagingHandler ph = new PagingHandler(pgvo, totalCount);

        m.addAttribute("list", list);
        m.addAttribute("ph", ph);


        return "/user/list";
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

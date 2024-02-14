//package ezen.bizqr.user.service;
//
//import ezen.bizqr.user.repository.UserMapper;
//import ezen.bizqr.user.security.UserVO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class CustomOAuth2UserService implements UserDetailsService {
//
//    private final UserMapper userMapper;
//
//    public CustomOAuth2UserService(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserVO uvo = userMapper.selectEmail(username);
//        uvo.setAuthList(userMapper.selectAuth(username));
//
//        log.info("login uvo >>>> {}", uvo);
//
//
//
//        return null;
//    }
//}

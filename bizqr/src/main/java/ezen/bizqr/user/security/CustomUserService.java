package ezen.bizqr.user.security;

import ezen.bizqr.user.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Slf4j
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO uvo = userMapper.selectEmail(username);
        uvo.setAuthList(userMapper.selectAuth(username));

        log.info("login uvo >>>> {}", uvo);

        boolean isOk = userMapper.updateLastLogin(username);

        log.info("lastLogin update >>> " + (isOk ? "OK" : "Fail"));

        return new AuthUser(uvo);
    }
}

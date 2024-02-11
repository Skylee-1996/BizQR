package ezen.bizqr.user.service;

import ezen.bizqr.user.repository.UserMapper;
import ezen.bizqr.user.security.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserVO selectEmail(String email) {
        return userMapper.selectEmail(email);
    }

    @Override
    public int userRegister(UserVO uvo) {
        int isOk = userMapper.userRegister(uvo);
        return userMapper.authUserRegister(uvo.getEmail());
    }
}

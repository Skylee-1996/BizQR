package ezen.bizqr.user.service;

import ezen.bizqr.user.security.UserVO;

import java.util.List;

public interface UserService {
    UserVO selectEmail(String email);

    int userRegister(UserVO uvo);

    List<UserVO> getUserList();

}

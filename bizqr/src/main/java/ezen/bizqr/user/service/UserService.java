package ezen.bizqr.user.service;

import ezen.bizqr.user.security.UserVO;

public interface UserService {
    UserVO selectEmail(String email);

    int userRegister(UserVO uvo);
}

package ezen.bizqr.user.service;

import ezen.bizqr.user.security.UserVO;

public interface UserService {
    UserVO checkEmail(String email);

    int userRegister(UserVO uvo);
}

package ezen.bizqr.user.repository;

import ezen.bizqr.user.security.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserVO checkEmail(String email);

    int userRegister(UserVO uvo);

    int authUserRegister(String email);
}

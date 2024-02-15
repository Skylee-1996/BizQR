package ezen.bizqr.user.repository;

import ezen.bizqr.user.security.AuthVO;
import ezen.bizqr.user.security.OAuthVO;
import ezen.bizqr.user.security.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserVO checkEmail(String email);

    int userRegister(UserVO uvo);

    int authUserRegister(String email);

    UserVO selectEmail(String username);

    List<AuthVO> selectAuth(String username);

    List<UserVO> getUserList();

    boolean updateLastLogin(String username);
    
    //소셜 계정 db 조회 확인용
    String selectSocialUser(String email);

    int registerSocialUser(OAuthVO oAuthVO);

    int userRegisterForSocial(UserVO uvo);

    int userModify(UserVO uvo);

    OAuthVO selectSocialUserDomain(String email);

    int socialUserModify(OAuthVO oAuthVO);
}

package ezen.bizqr.user.service;

import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.user.repository.UserMapper;
import ezen.bizqr.user.security.OAuthVO;
import ezen.bizqr.user.security.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<UserVO> getUserList(PagingVO pgvo) {

        List<UserVO> list = userMapper.getUserList(pgvo);
        for (UserVO uvo : list){
            uvo.setAuthList(userMapper.selectAuth(uvo.getEmail()));
        }
        return list;
    }

    @Override
    public int userModify(UserVO uvo) {
        return userMapper.userModify(uvo);
    }

    @Override
    public OAuthVO selectSocialUserDomain(String email) {
        return userMapper.selectSocialUserDomain(email);
    }

    @Override
    public int socialUserModify(OAuthVO oAuthVO) {
        return userMapper.socialUserModify(oAuthVO);
    }

    @Override
    public int getTotalUserCount(PagingVO pgvo) {
        return userMapper.getTotalUserCount(pgvo);
    }


}

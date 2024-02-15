package ezen.bizqr.user.service;

import ezen.bizqr.user.repository.UserMapper;
import ezen.bizqr.user.security.AuthVO;
import ezen.bizqr.user.security.OAuthVO;
import ezen.bizqr.user.security.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //구글로부터 받은 userRequest 데이터에 대한 후처리
        System.out.println("getClientRegistration : " + userRequest.getClientRegistration());
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info("Registration ID : " + registrationId);
        System.out.println("getAccessToken : " + userRequest.getAccessToken().getTokenValue());

        // 구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인을 완료 -> code를 리턴(OAuth-Client 라이브러리) -> AccessToken 요청
        // userRequest 정보 -> loadUser 함수 호출 -> 구글로부터 회원 프로필 받아준다.
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes : " + oAuth2User.getAttributes());

        String email = "";
        String pwd = "";
        String nickName = "";


        UserVO uvo = new UserVO();

        if(registrationId.equals("google")){
            email = (String) oAuth2User.getAttributes().get("email");
            pwd = (String) oAuth2User.getAttributes().get("sub");
            nickName = (String) oAuth2User.getAttributes().get("name");
            log.info(">>> google email : " + email);
            log.info(">>> google pwd : " + pwd);
            log.info(">>> google nickName : " + nickName);

            uvo.setIsSocial(1);
        }else if(registrationId.equals("naver")){
            email = (String) ((Map<String, Object>) oAuth2User.getAttributes().get("response")).get("email");
            pwd = (String) ((Map<String, Object>) oAuth2User.getAttributes().get("response")).get("id");
            nickName = (String) ((Map<String, Object>) oAuth2User.getAttributes().get("response")).get("nickname");
            log.info(">>> naver email : " + email);
            log.info(">>> naver pwd : " + pwd);
            log.info(">>> naver nickName : " + nickName);

            uvo.setIsSocial(2);
        }


        uvo.setEmail(email);
        uvo.setPwd(pwd);
        uvo.setNickName(nickName);
        log.info(">>> uvo >>> {}", uvo);

        String verifiedEmail = userMapper.selectSocialUser(email);

        if(verifiedEmail == null){
            //db에 저장되지 않은 경우
            OAuthVO oAuthVO = new OAuthVO();
            oAuthVO.setEmail(email);
            oAuthVO.setNickName(nickName);
            if(uvo.getIsSocial() == 1){
                oAuthVO.setProvider("google");
            }else if(uvo.getIsSocial() == 2){
                oAuthVO.setProvider("naver");
            }
            log.info(">>> oAuthVO >>> {}", oAuthVO);

            //social_user db에 삽입
            int isOk = userMapper.registerSocialUser(oAuthVO);

            //user db에 삽입
            isOk = userMapper.userRegisterForSocial(uvo);
            isOk = userMapper.authUserRegister(uvo.getEmail());

        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<AuthVO> authList = userMapper.selectAuth(email);

        //권한 부여
        for(AuthVO auth : authList){
            authorities.add(new SimpleGrantedAuthority(auth.getAuth()));
        }

        //새로운 속성 값으로 이메일과 권한을 설정
        Map<String, Object> updateAttributes = new HashMap<>(oAuth2User.getAttributes());
        updateAttributes.put("name", email);
        updateAttributes.put("authorities", authorities);

        //새로운 OAuth2User 객체 생성
        OAuth2User modifiedOAuth2User = new DefaultOAuth2User(authorities, updateAttributes, "name");


        return modifiedOAuth2User;
    }

}

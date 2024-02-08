package ezen.bizqr.user.security;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserVO {

    private String email;
    private String pwd;
    private String nickName;
    private String phoneNum;
    private String regDate;
    private String lastLogin;
    private int isSocial;
    private List<AuthVO> authList;

}

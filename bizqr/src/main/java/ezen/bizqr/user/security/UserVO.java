package ezen.bizqr.user.security;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserVO {

    private String email;
    private String pwd;
    private String nickName;
    private String regDate;
    private String lastLogin;

}

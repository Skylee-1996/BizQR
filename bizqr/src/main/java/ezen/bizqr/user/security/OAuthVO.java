package ezen.bizqr.user.security;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class OAuthVO {

    private String email;
    private String provider;
    private String nickName;
}

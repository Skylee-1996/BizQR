package ezen.bizqr.user.security;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AuthVO {

    private String email;
    private String auth;

}

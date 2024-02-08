package ezen.bizqr.user.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class AuthUser extends User {

    private static final long serialVersionUID = 1L;
    private UserVO uvo;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthUser(UserVO uvo){
        super(uvo.getEmail(), uvo.getPwd(),
                (uvo.getAuthList().stream().map(
                        authVO -> new SimpleGrantedAuthority(authVO.getAuth()))
                                .collect(Collectors.toList())));
        this.uvo = uvo;
    }
}

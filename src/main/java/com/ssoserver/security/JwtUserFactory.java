package com.ssoserver.security;

import java.util.List;
import java.util.stream.Collectors;

import com.ssoserver.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {

        JwtUser jwtUser =  new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return jwtUser;
    }

}

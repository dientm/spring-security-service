package com.ssoserver.service;

import com.ssoserver.model.UserDetail;
import com.ssoserver.model.request.AuthRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException;

    public UserDetail authorizeUser(AuthRequest authRequest);
}

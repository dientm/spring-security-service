package com.ssoserver.service.impl;

import com.ssoserver.model.UserDetail;
import com.ssoserver.model.entity.Organization;
import com.ssoserver.model.entity.Role;
import com.ssoserver.model.entity.Scope;
import com.ssoserver.model.entity.User;
import com.ssoserver.model.request.AuthRequest;
import com.ssoserver.repository.UserRepository;
import com.ssoserver.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return new UserDetail(
                    user.getId(),
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getUserapp().getAppId(),
                    new Organization(),
                    new ArrayList<Role>(),
                    new ArrayList<Scope>()

            );
        }
    }

    @Override
    public UserDetail authorizeUser(AuthRequest authRequest) {
        UserDetail userDetail = loadUserByUsername(authRequest.getUsername());

        if (userDetail.getPassword().equalsIgnoreCase(authRequest.getPassword().trim()) &&
                userDetail.getAppId().equalsIgnoreCase(authRequest.getClientId().trim())) {
            userDetail.setAuthenticated(true);
        } else {
            userDetail.setAuthenticated(false);
        }
        return userDetail;
    }


}

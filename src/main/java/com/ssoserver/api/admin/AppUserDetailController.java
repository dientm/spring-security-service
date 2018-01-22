package com.ssoserver.api.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "app-user-detail")
public class AppUserDetailController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUserDetail(Long appId, String userId) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserDetail(Long appId, String userId, Long roleId) {
        return null;
    }



}

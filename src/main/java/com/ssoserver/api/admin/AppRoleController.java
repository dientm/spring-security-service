package com.ssoserver.api.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="app_role")
public class AppRoleController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getListRoleInApp(@RequestParam Long appId) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAppRole(@RequestParam Long appId, RequestParam roleName) {
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAppRole(@RequestParam Long appId, Long[] roleIds) {
        return null;
    }
}

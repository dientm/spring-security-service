package com.ssoserver.service;

import com.ssoserver.model.entity.Role;

public interface RoleService {
    public Role getRoleByUsername(String username);
}

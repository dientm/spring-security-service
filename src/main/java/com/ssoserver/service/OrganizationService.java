package com.ssoserver.service;

import com.ssoserver.model.entity.Organization;

public interface OrganizationService {
    public Organization getOrgByUsername(String username);
}

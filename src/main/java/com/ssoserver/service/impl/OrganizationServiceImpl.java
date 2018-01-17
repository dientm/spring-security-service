package com.ssoserver.service.impl;

import com.ssoserver.model.entity.Organization;
import com.ssoserver.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Override
    public Organization getOrgByUsername(String username) {
        return null;
    }
}

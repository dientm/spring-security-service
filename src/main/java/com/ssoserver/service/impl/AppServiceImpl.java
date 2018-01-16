package com.ssoserver.service.impl;

import com.ssoserver.model.entity.App;
import com.ssoserver.service.AppService;
import org.springframework.stereotype.Service;

@Service
public class AppServiceImpl implements AppService {
    @Override
    public App getAppById(Long id) {
        return null;
    }

    @Override
    public boolean save(App app) {
        return false;
    }

    @Override
    public App update(App app) {
        return null;
    }
}

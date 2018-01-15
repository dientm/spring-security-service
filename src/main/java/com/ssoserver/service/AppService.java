package com.ssoserver.service;

import com.ssoserver.model.entity.App;
import org.springframework.stereotype.Service;

@Service
public interface AppService {

    App getAppById(Long id);

    boolean save(App app);

    App update(App app);


}

package com.ssoserver.api.admin;

import com.ssoserver.model.Response;
import com.ssoserver.model.entity.App;
import com.ssoserver.service.AppService;
import com.ssoserver.utils.ResponseGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apps")
public class AppController {
    final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppService appService;



    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response> getApp(String adminId, @RequestParam(required = false) Long appId, Pageable pageable) {
        if (null != appId) {
            App app = appService.getAppById(appId);
            return ResponseGenerator.generateResponse("200", app);
        } else {
            // get app list
            // TODO
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response> createApp(
            @RequestParam String appName,
            @RequestParam String appDomain,
            @RequestParam String serverKey,
            @RequestParam String redirectUrl,
            @RequestParam(required = false, defaultValue = "3600") Long tokenExpireDuration
    ) {
        // create new app
        App app = new App();
        app.setAppName("");
        // ....setting some properties
        // then saving to data
        appService.save(app);
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Response> updateApp() {
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteApp() {
        return null;
    }
}

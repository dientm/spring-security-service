package com.dientm.api.admin;

import com.dientm.model.Response;
import com.dientm.model.entity.App;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apps")
public class AppController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response> getApp(@RequestParam Long appId) {
        // get app by Id;
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response> createApp(
            @RequestParam String appName,
            @RequestParam String appDomain,
            @RequestParam String serverKey,
            @RequestParam String redirectUrl
    ) {
        // create new app
        App app = new App();
        app.setAppName("");
        // ....setting some properties
        // then saving to data
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

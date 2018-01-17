package com.ssoserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@EnableJpaRepositories
//@EnableJpaAuditing(modifyOnCreate = false)
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    //@Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // forward requests to /admin and /user to their index.html
                registry.addViewController("/api_docs").setViewName(
                        "forward:/api_docs/index.html");

//                registry.addViewController("/").setViewName(
//                        "forward:/api_docs/index.html");
            }
        };
    }

}

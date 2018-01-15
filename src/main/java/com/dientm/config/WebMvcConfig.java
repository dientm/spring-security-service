package com.dientm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableJpaRepositories
@EnableJpaAuditing(modifyOnCreate = false)
public class WebMvcConfig extends WebMvcConfigurerAdapter {


}

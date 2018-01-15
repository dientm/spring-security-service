package com.ssoserver.model.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

//@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name="app")
public class App implements Serializable {

    private Long id;
    private String clientId;
    private String clientSecret;
    private String appName;
    private String appDomain;
    private String redirectUri;
    private String tokenExpireDuration;
    private int status;



}

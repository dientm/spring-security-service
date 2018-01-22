package com.ssoserver.model.entity;

import javax.persistence.*;

@Entity
@Table(name="PERMISSION")
public class Scope_ {
    private Long id;

    private String url;

    private String api;

    private String method;

    private Permission scope;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @ManyToOne
    @JoinColumn(name = "scope_id")
    public Permission getScope() {
        return scope;
    }

    public void setScope(Permission scope) {
        this.scope = scope;
    }
}


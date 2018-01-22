package com.ssoserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssoserver.model.entity.Organization;
import com.ssoserver.model.entity.Role;
import com.ssoserver.model.entity.Scope;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"authenticated", "password"})
public class UserDetail implements Serializable{

    private Long id;
    private boolean authenticated;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String appId;
    private String org;
    private List<String> roles;
    private List<String> scopes;

    public UserDetail(Long id, String username, String firstname, String lastname, String password, String email, String appId, String org, List<String> roles, List<String> scopes) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.appId = appId;
        this.org  = org;
        this.roles = roles;
        this.scopes = scopes;
    }


    public void setOrg(String org) {
        this.org = org;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOrg() {
        return org;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}

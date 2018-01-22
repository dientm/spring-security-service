package com.ssoserver.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="SCOPE")
public class Scope {
    private Long id;

    private String url;

    private String api;

    private List<Role> roles;

    private List<Action> actions;


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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "scope_role", joinColumns = @JoinColumn(name = "scope_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "scope", cascade = CascadeType.ALL)
    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}


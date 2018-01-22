package com.ssoserver.model.entity;

import javax.persistence.*;

@Entity
@Table(name="ACTION")
public class Action {
    private Long id;
    private String action;
    private Scope scope;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    @ManyToOne
    @JoinColumn(name = "scope_id")
    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }
}

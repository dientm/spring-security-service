package com.ssoserver.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="ORGANIZATION")
public class Organization {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME", length=45)
    private String name;

    @Column(name="DOMAIN", length=45)
    private String domain;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}

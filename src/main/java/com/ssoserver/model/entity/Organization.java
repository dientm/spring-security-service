package com.ssoserver.model.entity;

import javax.persistence.*;

@Entity
@Table(name="ORGANIZATION")
public class Organization {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "org_seq")


}

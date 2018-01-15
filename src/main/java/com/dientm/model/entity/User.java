package com.dientm.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class User implements Serializable {
    private String id;
    private String userName;
    private String passwordHash;
    private String passwordSalt;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String orgId;
    private String subOrgId;
    private String status;

}

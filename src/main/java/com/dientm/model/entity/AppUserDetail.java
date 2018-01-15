package com.dientm.model.entity;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class AppUserDetail implements Serializable {
    private Long id;
    private Long appId;
    private Long roleId;
    private String userId;

}

package com.dientm.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class AppRole implements Serializable{
    private Long id;
    private Long appId;
    private String name;
    private String description;
    private boolean enableAppAdmin;
    private Integer level;

}

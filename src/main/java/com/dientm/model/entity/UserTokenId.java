package com.dientm.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserTokenId implements Serializable {
    private String accessToken;
    private String userId;

}

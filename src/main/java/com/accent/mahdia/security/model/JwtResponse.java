package com.accent.mahdia.security.model;

import com.accent.mahdia.dto.UserInfoDto;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final UserInfoDto user;

    public JwtResponse(UserInfoDto user) {
        this.user = user;
    }

    public UserInfoDto getUserInfoDto() {
        return this.user;
    }
}
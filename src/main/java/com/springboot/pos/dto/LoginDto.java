package com.springboot.pos.dto;

import com.springboot.pos.model.Cashier;

public class LoginDto {
    private String username;
    private String token;

    public LoginDto(UserDetailsDto user, String token) {
        this.username = user.getUsername();
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

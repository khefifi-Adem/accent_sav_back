package com.accent.mahdia.dto;

public class ResetPasswordDto {
    private String token;
    private String password;
    private String repeatPassword;

    public ResetPasswordDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResetPasswordDto(String token, String password, String repeatPassword) {
        this.token = token;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}

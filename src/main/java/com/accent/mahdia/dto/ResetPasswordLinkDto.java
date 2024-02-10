package com.accent.mahdia.dto;

public class ResetPasswordLinkDto {
    private String email;
    public ResetPasswordLinkDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResetPasswordLinkDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

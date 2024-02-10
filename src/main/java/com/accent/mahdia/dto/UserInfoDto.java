package com.accent.mahdia.dto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserInfoDto {

    private int id;
    private String username;
    @JsonIgnore
    private String password;
    private String rawPassword;
    private String img;
    private String phoneNumber;
    private String adress;
    private String name;
    private String email;
    private int createdBy;
    private Timestamp birthDate;
    private Timestamp createdDate;
    private int modifiedBy;
    private Timestamp modifiedDate;
    private String token;
    private AuthorityInfoDto[] authorities;
    private int isConfirmed;

    public UserInfoDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserInfoDto(int id, String username, String password, String rawPassword, String img, String phoneNumber, String adress, String name, String email, int createdBy, Timestamp birthDate, Timestamp createdDate, int modifiedBy, Timestamp modifiedDate, String token, AuthorityInfoDto[] authorities, int isConfirmed) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rawPassword = rawPassword;
        this.img = img;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
        this.name = name;
        this.email = email;
        this.createdBy = createdBy;
        this.birthDate = birthDate;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.token = token;
        this.authorities = authorities;
        this.isConfirmed = isConfirmed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthorityInfoDto[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(AuthorityInfoDto[] authorities) {
        this.authorities = authorities;
    }

    public int getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(int confirmed) {
        isConfirmed = confirmed;
    }
}

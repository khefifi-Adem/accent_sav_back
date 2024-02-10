package com.accent.mahdia.service;

import java.util.List;
import java.util.Map;

import com.accent.mahdia.dto.ResetPasswordDto;
import com.accent.mahdia.dto.ResetPasswordLinkDto;
import com.accent.mahdia.dto.UserInfoDto;
import com.accent.mahdia.entities.ConfirmationToken;
import com.accent.mahdia.entities.User;

public interface UserService {
    public List<User> findAllUsers();

    public UserInfoDto addUser(UserInfoDto userInfoDto);

    public UserInfoDto updateUser(UserInfoDto userInfoDto);

    public Boolean confirmUser (String token);

    public Boolean deleteUser(int id);

    public List<Map<String, Long>> userAgeStatus();

    UserInfoDto getUserById(Integer idUser);

    String getImgByIdUser(Integer idUser);

    UserInfoDto ResetPasswordUser(ResetPasswordDto resetPasswordDto);

    Boolean RequestResetLinkUser(ResetPasswordLinkDto email);

}

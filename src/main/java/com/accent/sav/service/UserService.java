package com.accent.sav.service;

import java.util.List;
import java.util.Map;

import com.accent.sav.dto.ResetPasswordDto;
import com.accent.sav.dto.ResetPasswordLinkDto;
import com.accent.sav.dto.UserInfoDto;
import com.accent.sav.entities.User;

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

package com.accent.mahdia.controller;

import java.util.List;
import java.util.Map;

import com.accent.mahdia.dto.ResetPasswordDto;
import com.accent.mahdia.dto.ResetPasswordLinkDto;
import com.accent.mahdia.dto.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accent.mahdia.entities.User;
import com.accent.mahdia.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserInfoDto addUser(@RequestBody UserInfoDto userInfoDto) {
        return userService.addUser(userInfoDto);
    }

    @RequestMapping(value = "/request-reset-link", method = RequestMethod.POST)
    public Boolean requestResetLink(@RequestBody ResetPasswordLinkDto email) {
        return userService.RequestResetLinkUser(email);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public UserInfoDto updateUser(@RequestBody UserInfoDto userInfoDto) {
        return userService.updateUser(userInfoDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody UserInfoDto getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/img/{id}", method = RequestMethod.GET)
    public @ResponseBody String getImageByIdUser(@PathVariable ("id") Integer id) {
        return userService.getImgByIdUser(id);
    }

    @RequestMapping(value = "/confirmation/{token}", method = RequestMethod.GET)
    public @ResponseBody Boolean confirmUser(@PathVariable ("token") String token) {
        return userService.confirmUser(token);
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.PUT)
    public UserInfoDto resetPasswordUser(@RequestBody ResetPasswordDto resetPasswordDto) {
        return userService.ResetPasswordUser(resetPasswordDto);
    }

    @RequestMapping(value = "/userstats", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Long>> userAgeStatus() {
        return userService.userAgeStatus();
    }
}


package com.accent.sav.controller;

import com.accent.sav.dto.AuthorityInfoDto;
import com.accent.sav.entities.Authority;
import com.accent.sav.service.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authorities")
@CrossOrigin
public class AuthoritiesController {
    @Autowired
    AuthoritiesService authoritiesService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<Authority> findAllAuthorities() {
        return authoritiesService.findAllAuthorities();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AuthorityInfoDto addCategory(@RequestBody AuthorityInfoDto authorityInfoDto) {
        return authoritiesService.addAuthority(authorityInfoDto);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public AuthorityInfoDto getAuthorityById(@PathVariable("id") Integer id) {
        return authoritiesService.getAuthorityById(id);
    }

}

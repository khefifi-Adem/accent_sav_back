package com.accent.sav.service;

import com.accent.sav.dto.AuthorityInfoDto;
import com.accent.sav.entities.Authority;

import java.util.List;

public interface AuthoritiesService {
    List<Authority> findAllAuthorities();

    public AuthorityInfoDto addAuthority(AuthorityInfoDto authorities);

    public AuthorityInfoDto getAuthorityById(Integer idAuthority);
}

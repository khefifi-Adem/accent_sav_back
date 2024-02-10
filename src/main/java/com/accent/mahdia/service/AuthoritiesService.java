package com.accent.mahdia.service;

import com.accent.mahdia.dto.AuthorityInfoDto;
import com.accent.mahdia.entities.Authority;

import java.util.List;

public interface AuthoritiesService {
    List<Authority> findAllAuthorities();

    public AuthorityInfoDto addAuthority(AuthorityInfoDto authorities);

    public AuthorityInfoDto getAuthorityById(Integer idAuthority);
}

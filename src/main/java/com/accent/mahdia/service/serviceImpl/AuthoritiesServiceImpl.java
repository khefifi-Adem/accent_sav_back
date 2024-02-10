package com.accent.mahdia.service.serviceImpl;

import com.accent.mahdia.dto.AuthorityInfoDto;
import com.accent.mahdia.entities.Authority;
import com.accent.mahdia.repository.AuthoritiesRepository;
import com.accent.mahdia.security.exception.ResourceAlreadyExistException;
import com.accent.mahdia.service.AuthoritiesService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    protected ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(Authority.class);

    @Override
    public List<Authority> findAllAuthorities() {
        return authoritiesRepository.findAll();
    }

    @Override
    public AuthorityInfoDto addAuthority(AuthorityInfoDto authorities) {
        if (authoritiesRepository.existsById(authorities.getId())) {
            // throw exception
            throw new ResourceAlreadyExistException("Existing id ");
        }

        Authority authority = this.mapper.map(authorities, Authority.class);
        Authority authorityAdded = this.authoritiesRepository.save(authority);
        this.authoritiesRepository.save(authorityAdded);
        return this.mapper.map(authorityAdded, AuthorityInfoDto.class);

    }

    @Override
    public AuthorityInfoDto getAuthorityById(Integer idAuthority) {
        Authority authority = authoritiesRepository.findByIdAuthority(idAuthority);
        if (authority == null) {
            logger.error("Authority with ID {} does not exist.", idAuthority);
            throw new ResourceNotFoundException("ce user n'existe pas !");
        } else {
            logger.info("Retrieved authority with ID {} successfully.", idAuthority);
        }
        return this.mapper.map(authority, AuthorityInfoDto.class);

    }
}

package com.accent.mahdia.security.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accent.mahdia.dto.UserInfoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.accent.mahdia.entities.User;
import com.accent.mahdia.repository.UserRepository;
import com.accent.mahdia.security.config.JwtTokenUtil;
import com.accent.mahdia.security.exception.ResourceNotFoundException;
import com.accent.mahdia.security.model.JwtRequest;
import com.accent.mahdia.security.model.JwtResponse;
import com.accent.mahdia.security.service.JwtUserDetailsService;
import com.accent.mahdia.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userServcie;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if (userDetails == null)
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        final String token = jwtTokenUtil.generateToken(userDetails);
        User user = userRepository.findByUsername(userDetails.getUsername());
        UserInfoDto userInfo = mapper.map(user, UserInfoDto.class);
        userInfo.setToken(token);

        return ResponseEntity.ok(userInfo);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserInfoDto userInfoDto) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getRawPassword()));

        if (usernameExists(userInfoDto.getEmail())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            if (userRepository.existsByUsername(userInfoDto.getUsername())) {
                // throw exception
                throw new ResourceNotFoundException("Existing pseudo ");

            }
            userServcie.addUser(userInfoDto);
            return ResponseEntity.ok(new JwtResponse(userInfoDto));
        }

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        System.out.println("This user logged out: " + authentication.getName());

        response.sendRedirect("/logout_success");
    }

    private boolean usernameExists(final String username) {
        return userRepository.findByUsername(username) != null;
    }
}

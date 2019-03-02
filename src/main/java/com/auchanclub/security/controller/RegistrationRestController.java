package com.auchanclub.security.controller;

import com.auchanclub.model.security.User;
import com.auchanclub.security.JwtAuthenticationRequest;
import com.auchanclub.security.JwtTokenUtil;
import com.auchanclub.security.RegistrationRequest;
import com.auchanclub.security.service.JwtAuthenticationResponse;
import com.auchanclub.security.service.JwtUserDetailsService;
import com.auchanclub.security.service.RegistrationValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RegistrationRestController {

    @Autowired
    RegistrationValidate validator;

    @Autowired
    JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody RegistrationRequest registrationRequest) throws RegistrationException {

        System.out.println(registrationRequest.getUsername());
        User newUser = new User();
        newUser.setUsername(registrationRequest.getUsername());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        newUser.setFirstname(registrationRequest.getUsername());
        newUser.setLastname(registrationRequest.getUsername());
        newUser.setEnabled(true);
        newUser.setLastPasswordResetDate(new Date());
        if(validator.validateUsername(newUser)) {

            final UserDetails userDetails = userDetailsService.loadUserByUsername(registrationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        }
        else
        {
            return ResponseEntity.ok("User exists");
        }
    }
}

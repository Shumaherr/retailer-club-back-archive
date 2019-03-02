package com.auchanclub.security.service;

import com.auchanclub.model.security.Authority;
import com.auchanclub.model.security.Role;
import com.auchanclub.model.security.User;
import com.auchanclub.security.repository.AuthorityRepository;
import com.auchanclub.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RegistrationValidate {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public boolean validateUsername(User user)
    {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if(userFromDB!=null)
        {
            return false;
        }
        else
        {
            Authority authority = new Authority();
            authority.setId(user.getId());
            authority.setName(Role.ROLE_USER);
            user.setAuthorities(Collections.singletonList(authority));
            authorityRepository.saveAndFlush(authority);
            userRepository.saveAndFlush(user);
            return true;
        }
    }
}

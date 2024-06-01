package com.contactmanager.scm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.contactmanager.scm.Repositories.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return user.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User name not found with name : "+username));
    }

}

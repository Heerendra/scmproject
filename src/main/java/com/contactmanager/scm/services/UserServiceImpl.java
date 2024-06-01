package com.contactmanager.scm.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.contactmanager.scm.Repositories.UserRepo;
import com.contactmanager.scm.entities.User;
import com.contactmanager.scm.helpers.AppContants;
import com.contactmanager.scm.helpers.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        //Generation of String Id.
        user.setUserId(UUID.randomUUID().toString());

        //Encode Password with password encoder while saving 
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //providing Role List to user.
        user.setRoleList(List.of(AppContants.ROLE_USER));

        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {

        User u = userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User Not Found."));
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setPhone(user.getPhone());
        u.setProfilePic(user.getProfilePic());
        u.setAbout(user.getAbout());
        u.setEnabled(user.isEnabled());
        u.setEmailVerified(user.isEmailVerified());
        u.setProvider(user.getProvider());
        u.setProviderUserId(user.getProviderUserId());

        User save = userRepo.save(u);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User u = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found."));
        userRepo.delete(u);
    }

    @Override
    public boolean isUserExist(String id) {
        User u = userRepo.findById(id).orElse(null);
        return u != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User u = userRepo.findByEmail(email).orElse(null);
        return u != null ? true : false;
    }

    @Override
    public List<User> getAllusers() {
        return userRepo.findAll();
    }

}

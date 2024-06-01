package com.contactmanager.scm.services;

import java.util.List;
import java.util.Optional;

import com.contactmanager.scm.entities.User;

public interface UserServices {

    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String id);
    boolean isUserExistByEmail(String email);
    List<User> getAllusers();

}

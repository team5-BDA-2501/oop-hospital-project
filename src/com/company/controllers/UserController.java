package com.company.controllers;

import com.company.models.User;
import com.company.repositories.interfaces.IUserRepository;

public class UserController {
    private final IUserRepository userRepository;

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User loginUser(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }

    public boolean registerUser(String name, String surname, String username, String password, boolean gender) {
        User user = new User(name, surname, username, password, gender);
        return userRepository.createUser(user);
    }
}
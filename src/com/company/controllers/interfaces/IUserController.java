package com.company.controllers.interfaces;

import com.company.models.User;

public interface IUserController {
    boolean registerUser(String name, String surname, String username, String password, boolean gender);
    User loginUser(String username, String password);
}
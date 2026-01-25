package com.company.repositories.interfaces;

import com.company.models.User;

public interface IUserRepository {
    User getUserByUsernameAndPassword(String username, String password);
    boolean createUser(User user);
}
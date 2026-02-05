package com.company.repositories.interfaces;

import com.company.models.User;
import java.util.List;

public interface IUserRepository {
    User getUserByUsernameAndPassword(String username, String password);
    boolean createUser(User user);
    List<User> getAllUsers(); // NEW
}
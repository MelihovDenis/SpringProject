package com.project.services;

import com.project.models.Manager;
import com.project.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void delete(int id);
    void addUser(User user);
    User findByLogin(String login);
    public String getUsername();
    void addManager(Manager manager);
}

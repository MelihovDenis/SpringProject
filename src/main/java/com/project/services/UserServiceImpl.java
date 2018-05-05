package com.project.services;

import com.project.dao.UserDAO;
import com.project.models.Manager;
import com.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
        userDAO.setRoleById(user.getUser_id());
    }
    @Override
    public void addManager(Manager manager) {
        userDAO.addManager(manager);
        userDAO.setMRoleById(manager.getUser_id());
    }

    @Override
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    public String getUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return null;
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }


}

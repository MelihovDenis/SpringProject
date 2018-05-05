package com.project.controllers;

import com.project.models.Manager;
import com.project.models.User;
import com.project.services.SecurityService;
import com.project.services.UserService;
import com.project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    UserValidator userValidator;

    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "index"; }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        service.delete(id);
        return "redirect:/"; }

    @PostMapping("/addUser/")
    public String addUser( User user) {
        service.addUser(user);
        return "redirect:/"; }

    @PostMapping("/addManager/")
    public String addManager(Manager manager) {
        service.addManager(manager);
        return "redirect:/"; }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration"; }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        service.addUser(userForm);
        securityService.autoLogin(userForm.getLogin(), userForm.getPassword());
        System.out.println(service.getUsername());
        return "redirect:/"; }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }

}

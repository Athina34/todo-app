package com.nyc.todoapp.controller;

import com.nyc.todoapp.model.User;
import com.nyc.todoapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Εμφάνιση σελίδας Login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Εμφάνιση σελίδας εγγραφής
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Επεξεργασία εγγραφής νέου χρήστη
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/login?success";
    }
}
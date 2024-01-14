package com.isbd.coursework.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserDetailsManager userDetailsManager;

    RegistrationController(UserDetailsService manager) {
        this.userDetailsManager = (UserDetailsManager) manager;
    }

    @GetMapping
    public String showRegistrationForm(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "register";
    }

    @PostMapping
    @SuppressWarnings("deprecation")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password
    ) {
        UserDetails newUser = User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles("USER")
                .build();
        try {
            userDetailsManager.createUser(newUser);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            return String.format("redirect:/register?error=%s", e.getMessage());
        }
    }
}


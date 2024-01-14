package com.isbd.coursework.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserManagement {

    @GetMapping("/me")
    public String showUser(Model model, Principal principal) {
        UsernamePasswordAuthenticationToken userDetails = (UsernamePasswordAuthenticationToken) principal;
        model.addAttribute("userDetails", userDetails);
        return "me";
    }
}

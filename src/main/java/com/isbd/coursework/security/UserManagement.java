package com.isbd.coursework.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserManagement {

    @GetMapping("/userlist")
    public String showUser(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        return "me";
    }
}

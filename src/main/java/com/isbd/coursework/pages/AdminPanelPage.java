package com.isbd.coursework.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/panel")
public class AdminPanelPage {

    @GetMapping
    public String viewPanel() {
        return "admin_panel";
    }

}

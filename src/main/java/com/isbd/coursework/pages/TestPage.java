package com.isbd.coursework.pages;

import com.isbd.coursework.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestPage {
    @Autowired
    WorkerService service;

    @GetMapping
    public String viewWorkers(ModelMap model) {
        model.addAttribute("workers", service.getWorkers());
        return "test";
    }

}

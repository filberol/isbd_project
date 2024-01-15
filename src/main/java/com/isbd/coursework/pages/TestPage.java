package com.isbd.coursework.pages;

import com.isbd.coursework.services.RailwayStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestPage {
    @Autowired
    RailwayStationService service;

    @GetMapping
    public String viewWorkers(ModelMap model) {
        model.addAttribute("stations", service.getAllStations(10));
        return "test";
    }

}

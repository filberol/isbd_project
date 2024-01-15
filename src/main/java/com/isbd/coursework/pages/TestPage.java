package com.isbd.coursework.pages;

import com.isbd.coursework.api.RailwayStationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
//TODO Get rid of page
public class TestPage {

    RailwayStationApi stationApi;

    TestPage(RailwayStationApi stationApi) {
        this.stationApi = stationApi;
    }

    @GetMapping
    public String viewWorkers(ModelMap model) {
        model.addAttribute("stations", stationApi.getAllStations(10));
        return "test";
    }

}

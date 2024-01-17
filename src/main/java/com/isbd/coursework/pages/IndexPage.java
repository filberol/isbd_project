package com.isbd.coursework.pages;

import com.isbd.coursework.api.RailwayStationApi;
import com.isbd.coursework.entities.RailwayStation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping({"/", "/home"})
public class IndexPage {

    private final RailwayStationApi railwayStationApi;

    IndexPage(
            RailwayStationApi railwayStationApi
    ) {
        this.railwayStationApi = railwayStationApi;
    }

    @GetMapping
    public String viewPanel(
            @RequestParam(defaultValue = "1") Integer stationId,
            Model model
    ) {
        RailwayStation station = railwayStationApi.getStationById(stationId);
        List<RailwayStation> related = railwayStationApi.getStationsRelated(stationId);
        model.addAttribute("station", station);
        model.addAttribute("related", related);
        return "index";
    }
}

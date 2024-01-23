package com.isbd.coursework.pages;

import com.isbd.coursework.api.RepairTeamRoutesApi;
import com.isbd.coursework.entities.dto.TeamRouteDescription;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/team_routes")
public class RepairTeamRoutesPage {

    private final RepairTeamRoutesApi routesApi;

    RepairTeamRoutesPage(
            RepairTeamRoutesApi routesApi
    ) {
        this.routesApi = routesApi;
    }

    @GetMapping
    public String viewTeamRoutes(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "1") Integer repairBase,
            Model model
    ) {
        List<TeamRouteDescription> routes = routesApi.getTeamRouteDescriptionsFromBase(repairBase);
        model.addAttribute("routes", routes);
        model.addAttribute("page", page);
        return "repair_team_routes";
    }
}

package com.isbd.coursework.pages;

import com.isbd.coursework.api.RepairTeamApi;
import com.isbd.coursework.api.RepairTeamRoutesApi;
import com.isbd.coursework.entities.RepairTeamMember;
import com.isbd.coursework.entities.dto.TeamRouteDescription;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("api/brigade/routes")
public class RouteBusinessPage {

    private final RepairTeamApi repairTeamApi;
    private final RepairTeamRoutesApi repairTeamRoutesApi;

    RouteBusinessPage(
            RepairTeamApi repairTeamApi,
            RepairTeamRoutesApi repairTeamRoutesApi
    ) {
        this.repairTeamApi = repairTeamApi;
        this.repairTeamRoutesApi = repairTeamRoutesApi;
    }

    @GetMapping
    public String viewRouteBusinessPage(
            @RequestParam(defaultValue = "1") Integer teamId,
            Model model
    ) {
        List<TeamRouteDescription> routes = repairTeamRoutesApi.getTeamRoutesDescriptionsByTeamId(teamId);
        List<RepairTeamMember> members = repairTeamApi.getRepairTeamMembersById(teamId);
        RepairTeamMember head = repairTeamApi.getTeamHeadById(teamId);

        model.addAttribute("routes", routes);
        model.addAttribute("members", members);
        model.addAttribute("head", head);
        return "route_business";
    }
}

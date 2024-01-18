package com.isbd.coursework.pages;

import com.isbd.coursework.api.*;
import com.isbd.coursework.entities.*;
import com.isbd.coursework.entities.dto.TeamRouteDescription;
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
    private final WarehouseApi warehouseApi;
    private final RepairBaseApi repairBaseApi;
    private final CompanyApi companyApi;
    private final RepairTeamRoutesApi routesApi;

    IndexPage(
            RailwayStationApi railwayStationApi,
            WarehouseApi warehouseApi,
            RepairBaseApi repairBaseApi,
            CompanyApi companyApi,
            RepairTeamRoutesApi routesApi
    ) {
        this.railwayStationApi = railwayStationApi;
        this.repairBaseApi = repairBaseApi;
        this.warehouseApi = warehouseApi;
        this.companyApi = companyApi;
        this.routesApi = routesApi;
    }

    @GetMapping
    public String viewPanel(
            @RequestParam(defaultValue = "1") Integer stationId,
            Model model
    ) {
        RailwayStation station = railwayStationApi.getStationById(stationId);
        List<RailwayStation> related = railwayStationApi.getStationsRelated(stationId);
        Warehouse warehouse = warehouseApi.getWarehouseByStationId(stationId);
        RepairBase repairBase = repairBaseApi.getRepairBaseByStationId(stationId);
        if (station != null) {
            Company company = companyApi.getCompanyById(station.ownerId());
            model.addAttribute("company", company);

        }
        if (warehouse != null) {
            List<WarehouseResourceAllocation> resources = warehouseApi.getResourceAllocations(warehouse.id());
            model.addAttribute("resources", resources);
        }
        if (repairBase != null) {
            List<TeamRouteDescription> routes = routesApi.getTeamRouteDescriptionsFromBase(repairBase.id());
            model.addAttribute("routes", routes);
        }
        model.addAttribute("station", station);
        model.addAttribute("related", related);
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("repairBase", repairBase);
        return "index";
    }
}

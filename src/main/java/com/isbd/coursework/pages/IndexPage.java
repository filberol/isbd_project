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
    private final SegmentFaultApi faultApi;

    IndexPage(
            RailwayStationApi railwayStationApi,
            WarehouseApi warehouseApi,
            RepairBaseApi repairBaseApi,
            CompanyApi companyApi,
            RepairTeamRoutesApi routesApi,
            SegmentFaultApi faultApi
    ) {
        this.railwayStationApi = railwayStationApi;
        this.repairBaseApi = repairBaseApi;
        this.warehouseApi = warehouseApi;
        this.companyApi = companyApi;
        this.routesApi = routesApi;
        this.faultApi = faultApi;
    }

    @GetMapping
    public String viewPanel(
            @RequestParam(defaultValue = "1") Integer stationId,
            @RequestParam(defaultValue = "") String stationName,
            Model model
    ) {
        RailwayStation station;
        if (stationName.equals("")) {
            station = railwayStationApi.getStationById(stationId);
        } else {
            List<RailwayStation> suggestedStations = railwayStationApi.getStationNameLike(stationName);
            station = suggestedStations.get(0);
            model.addAttribute("suggested", suggestedStations);
        }
        if (station != null) {
            List<RailwayStation> related = railwayStationApi.getStationsRelated(station.id());
            List<SegmentFault> closeFaults = faultApi.getFaultsCloseToStation(station.id());
            List<SegmentFault> criticalFaults = faultApi.getNotRepairedCriticalFaults();
            Warehouse warehouse = warehouseApi.getWarehouseByStationId(station.id());
            RepairBase repairBase = repairBaseApi.getRepairBaseByStationId(station.id());
            Company company = companyApi.getCompanyById(station.ownerId());
            model.addAttribute("related", related);
            model.addAttribute("closeFaults", closeFaults);
            model.addAttribute("criticalFaults", criticalFaults);
            model.addAttribute("warehouse", warehouse);
            model.addAttribute("repairBase", repairBase);
            model.addAttribute("company", company);
            if (warehouse != null) {
                List<WarehouseResourceAllocation> resources = warehouseApi.getResourceAllocations(warehouse.id());
                model.addAttribute("resources", resources);
            }
            if (repairBase != null) {
                List<TeamRouteDescription> routes = routesApi.getTeamRouteDescriptionsFromBase(repairBase.id());
                model.addAttribute("routes", routes);
            }
        }
        model.addAttribute("station", station);
        return "index";
    }
}

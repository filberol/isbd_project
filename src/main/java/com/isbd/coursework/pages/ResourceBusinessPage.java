package com.isbd.coursework.pages;

import com.isbd.coursework.api.*;
import com.isbd.coursework.entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("api/brigade/resources")
public class ResourceBusinessPage {

    private final RailwayStationApi railwayStationApi;
    private final WarehouseApi warehouseApi;

    ResourceBusinessPage(
            WarehouseApi warehouseApi,
            RailwayStationApi repairTeamApi
    ) {
        this.warehouseApi = warehouseApi;
        this.railwayStationApi = repairTeamApi;
    }

    @GetMapping
    public String viewRouteBusinessPage(
            @RequestParam(defaultValue = "1") Integer stationId,
            Model model
    ) {
        RailwayStation station = railwayStationApi.getStationById(stationId);
        if (station != null) {
            Warehouse warehouse = warehouseApi.getWarehouseByStationId(station.id());
            model.addAttribute("warehouse", warehouse);
            if (warehouse != null) {
                List<WarehouseResourceAllocation> resources = warehouseApi.getResourceAllocations(warehouse.id());
                model.addAttribute("resources", resources);
            }
        }

        model.addAttribute("station", station);
        return "resource_business";
    }
}

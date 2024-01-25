package com.isbd.coursework.pages;

import com.isbd.coursework.api.RailwayStationApi;
import com.isbd.coursework.api.ResourceTransportationApi;
import com.isbd.coursework.api.WarehouseApi;
import com.isbd.coursework.entities.RailwayStation;
import com.isbd.coursework.entities.ResourceTransportation;
import com.isbd.coursework.entities.Warehouse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("api/brigade/resources/transport")
public class TransportBusinessPage {

    private final RailwayStationApi railwayStationApi;
    private final WarehouseApi warehouseApi;
    private final ResourceTransportationApi resourceTransportationApi;

    TransportBusinessPage(
            WarehouseApi warehouseApi,
            RailwayStationApi repairTeamApi,
            ResourceTransportationApi resourceTransportationApi
    ) {
        this.warehouseApi = warehouseApi;
        this.railwayStationApi = repairTeamApi;
        this.resourceTransportationApi = resourceTransportationApi;
    }

    @GetMapping
    public String viewTransportBusinessPage(
            @RequestParam(defaultValue = "1") Integer stationId,
            Model model
    ) {
        RailwayStation station = railwayStationApi.getStationById(stationId);
        if (station != null) {
            Warehouse warehouse = warehouseApi.getWarehouseByStationId(station.id());
            model.addAttribute("warehouse", warehouse);
            if (warehouse != null) {
                List<ResourceTransportation> transportations =
                        resourceTransportationApi.getResourceTransportationsFromWarehouse(warehouse.id());
                model.addAttribute("transportations", transportations);
            }
        }

        model.addAttribute("station", station);
        return "transport_business";
    }
}

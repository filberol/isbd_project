package com.isbd.coursework.pages;

import com.isbd.coursework.api.RailwayStationApi;
import com.isbd.coursework.api.SegmentFaultApi;
import com.isbd.coursework.api.SiteFaultFixationApi;
import com.isbd.coursework.api.WarehouseApi;
import com.isbd.coursework.entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("api/brigade/faults")
public class FaultsBusinessPage {

    private final SiteFaultFixationApi siteFaultFixationApi;
    private final SegmentFaultApi segmentFaultApi;

    FaultsBusinessPage(
            SiteFaultFixationApi siteFaultFixationApi,
            SegmentFaultApi segmentFaultApi
    ) {
        this.siteFaultFixationApi = siteFaultFixationApi;
        this.segmentFaultApi = segmentFaultApi;
    }

    @GetMapping
    public String viewFaultsBusinessPage(
            @RequestParam(defaultValue = "1") Integer routeId,
            Model model
    ) {
        List<SiteFaultFixation> fixations = siteFaultFixationApi.getFixationsByRouteId(routeId);

        model.addAttribute("fixations", fixations);
        return "faults_business";
    }

    @GetMapping("/{faultId}")
    public String viewSegmentFault(
            @PathVariable Integer faultId,
            Model model
    ) {
        SegmentFault fault = segmentFaultApi.getFaultById(faultId);
        model.addAttribute("fault", fault);
        return "segment_fault";
    }
}

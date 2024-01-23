package com.isbd.coursework.pages;

import com.isbd.coursework.api.SegmentFaultApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/critical")
public class CriticalFaultsPage {

    SegmentFaultApi faultApi;

    CriticalFaultsPage(
            SegmentFaultApi faultApi
    ) {
        this.faultApi = faultApi;
    }

    @GetMapping
    public String viewWorkers(
            @RequestParam(defaultValue = "1") Integer page,
            ModelMap model
    ) {
        model.addAttribute("criticalFaults", faultApi.getNotRepairedCriticalFaults());
        model.addAttribute("page", page);
        return "critical_faults";
    }

}

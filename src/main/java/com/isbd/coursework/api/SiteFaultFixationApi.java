package com.isbd.coursework.api;

import com.isbd.coursework.entities.SiteFaultFixation;

import java.util.List;

public interface SiteFaultFixationApi {
    List<SiteFaultFixation> getFixationsByRouteId(Integer routeId);
}

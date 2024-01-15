package com.isbd.coursework.api;

import com.isbd.coursework.entities.InspectionRepairSite;

import java.util.List;

public interface InspectionRepairSiteApi {
    List<InspectionRepairSite> getInspectionsByRouteId(Integer routeId);
    List<InspectionRepairSite> getRepairsByRouteId(Integer routeId);
    List<InspectionRepairSite> getRepairsByRailwaySegmentId(Integer segmentId);
}

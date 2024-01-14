package com.isbd.coursework.entities;

import com.isbd.coursework.entities.enums.SiteVisitType;

import java.time.ZonedDateTime;

public record InspectionRepairSite(
        Integer id,
        Integer routeId,
        Integer railwaySegmentId,
        Integer positionPointKm,
        ZonedDateTime arrivedAt,
        SiteVisitType typeSiteAction
) {
}

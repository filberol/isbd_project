package com.isbd.coursework.entities;

import com.isbd.coursework.entities.enums.SiteVisitType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public record InspectionRepairSite(
        Integer id,
        Integer routeId,
        Integer railwaySegmentId,
        Integer positionPointKm,
        Timestamp arrivedAt,
        SiteVisitType typeSiteAction
) {
    public static InspectionRepairSite fromSet(ResultSet set) throws SQLException {
        return new InspectionRepairSite(
                set.getInt("id"),
                set.getInt("route_id"),
                set.getInt("railway_segment_id"),
                set.getInt("position_point_km"),
                set.getTimestamp("arrived_at"),
                SiteVisitType.valueOf(set.getString("type_site_action"))
        );
    }
}

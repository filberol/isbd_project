package com.isbd.coursework.entities;

import com.isbd.coursework.entities.enums.FaultClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public record SiteFaultFixation(
        Integer id,
        Integer segmentFaultId,
        Integer routeId,
        Timestamp foundAt,
        FaultClass faultClass
) {
    public static SiteFaultFixation fromSet(ResultSet set) throws SQLException {
        return new SiteFaultFixation(
                set.getInt("id"),
                set.getInt("segment_fault_id"),
                set.getInt("route_id"),
                set.getTimestamp("found_at"),
                FaultClass.valueOf(set.getString("fault_class"))
        );
    }
}

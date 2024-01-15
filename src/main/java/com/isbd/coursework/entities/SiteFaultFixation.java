package com.isbd.coursework.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public record SiteFaultFixation(
        Integer id,
        Integer routeId,
        Timestamp plannedAt,
        Timestamp departedAt,
        Timestamp arrivedAt
) {
    public static SiteFaultFixation fromSet(ResultSet set) throws SQLException {
        return new SiteFaultFixation(
                set.getInt("id"),
                set.getInt("route_id"),
                set.getTimestamp("planned_at"),
                set.getTimestamp("departed_at"),
                set.getTimestamp("arrived_at_at")
        );
    }
}

package com.isbd.coursework.entities.dto;

import com.isbd.coursework.entities.RepairTeamRoute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public record TeamRouteDescription(
        Integer repairTeamId,
        Integer routeId,
        Timestamp plannedAt,
        Timestamp departedAt,
        Timestamp arrivedAt,
        String name
) {
    public static TeamRouteDescription fromSet(ResultSet set) throws SQLException {
        return new TeamRouteDescription(
                set.getInt("repair_team_id"),
                set.getInt("route_id"),
                set.getTimestamp("planned_at"),
                set.getTimestamp("departed_at"),
                set.getTimestamp("arrived_at"),
                set.getString("name")
        );
    }
}

package com.isbd.coursework.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public record RepairBase(
        Integer id,
        Integer stationId,
        Integer sizeTeams,
        Integer currTeamsHosted
) {
    public static RepairBase fromSet(ResultSet set) throws SQLException {
        return new RepairBase(
                set.getInt("id"),
                set.getInt("station_id"),
                set.getInt("size_teams"),
                set.getInt("curr_teams_hosted")
        );
    }
}

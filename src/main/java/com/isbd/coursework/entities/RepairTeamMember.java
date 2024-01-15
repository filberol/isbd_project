package com.isbd.coursework.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public record RepairTeamMember(
        Integer id,
        String name,
        Integer repairTeamId
) {
    public static RepairTeamMember fromSet(ResultSet set) throws SQLException {
        return new RepairTeamMember(
                set.getInt("id"),
                set.getString("name"),
                set.getInt("repair_team_id")
        );
    }
}

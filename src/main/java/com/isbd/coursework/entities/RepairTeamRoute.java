package com.isbd.coursework.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public record RepairTeamRoute(
        Integer id,
        Integer repairTeamId,
        Integer fromBaseId,
        Integer toBaseId
) {
    public static RepairTeamRoute fromSet(ResultSet set) throws SQLException {
        return new RepairTeamRoute(
                set.getInt("id"),
                set.getInt("repair_team_id"),
                set.getInt("from_base_id"),
                set.getInt("to_base_id")
        );
    }
}

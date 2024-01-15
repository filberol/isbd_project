package com.isbd.coursework.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public record RailwayStation(
        Integer id,
        String name,
        Integer ownerId
) {
    public static RailwayStation fromSet(ResultSet set) throws SQLException {
        return new RailwayStation(
                set.getInt("id"),
                set.getString("name"),
                set.getInt("owner_id")
        );
    }
}

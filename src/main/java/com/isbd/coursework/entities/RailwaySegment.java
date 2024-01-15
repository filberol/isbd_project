package com.isbd.coursework.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public record RailwaySegment(
        Integer id,
        Integer fromRs,
        Integer toRs,
        Integer lengthKm
) {
    public static RailwaySegment fromSet(ResultSet set) throws SQLException {
        return new RailwaySegment(
                set.getInt("id"),
                set.getInt("from_rs"),
                set.getInt("to_rs"),
                set.getInt("length_km")
        );
    }
}

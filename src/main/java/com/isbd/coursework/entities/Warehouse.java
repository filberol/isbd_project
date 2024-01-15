package com.isbd.coursework.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public record Warehouse(
        Integer id,
        Integer stationId,
        Integer resourceAvailableKm
) {
    public static Warehouse fromSet(ResultSet set) throws SQLException {
        return new Warehouse(
                set.getInt("id"),
                set.getInt("station_id"),
                set.getInt("resource_available_km")
        );
    }
}

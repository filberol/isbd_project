package com.isbd.coursework.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public record ResourceTransportation(
        Integer id,
        Integer fromWarehouseId,
        Integer toWarehouseId,
        Timestamp startAt,
        Timestamp finishAt,
        Integer resourceTransportationKm
) {
    public static ResourceTransportation fromSet(ResultSet set) throws SQLException {
        return new ResourceTransportation(
                set.getInt("id"),
                set.getInt("from_warehouse_id"),
                set.getInt("to_warehouse_id"),
                set.getTimestamp("start_at"),
                set.getTimestamp("finish_at"),
                set.getInt("resources_transportation_km")
        );
    }
}

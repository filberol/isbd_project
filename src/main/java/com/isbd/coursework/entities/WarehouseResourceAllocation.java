package com.isbd.coursework.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public record WarehouseResourceAllocation(
        Integer id,
        Integer warehouse_id,
        Integer resourceAllocatedKm,
        Timestamp allocatedAt
) {
    public static WarehouseResourceAllocation fromSet(ResultSet set) throws SQLException {
        return new WarehouseResourceAllocation(
                set.getInt("id"),
                set.getInt("warehouse_id"),
                set.getInt("resources_allocated_km"),
                set.getTimestamp("allocated_at")
        );
    }
}

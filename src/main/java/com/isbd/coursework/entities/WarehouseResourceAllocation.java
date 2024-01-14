package com.isbd.coursework.entities;

import java.time.ZonedDateTime;

public record WarehouseResourceAllocation(
        Integer id,
        Integer warehouse_id,
        Integer resourceAllocatedKm,
        ZonedDateTime allocatedAt
) {
}

package com.isbd.coursework.api;

import com.isbd.coursework.entities.Warehouse;
import com.isbd.coursework.entities.WarehouseResourceAllocation;

import java.sql.Timestamp;
import java.util.List;

public interface WarehouseApi {
    Warehouse getWarehouseByStationId(Integer stationId);
    List<WarehouseResourceAllocation> getResourceAllocations(Integer warehouseId);
    List<WarehouseResourceAllocation> getResourceAllocationsBetween(
            Integer warehouseId, Timestamp from, Timestamp to
            );
}

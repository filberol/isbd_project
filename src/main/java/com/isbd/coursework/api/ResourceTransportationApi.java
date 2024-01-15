package com.isbd.coursework.api;

import com.isbd.coursework.entities.ResourceTransportation;

import java.sql.Timestamp;
import java.util.List;

public interface ResourceTransportationApi {
    List<ResourceTransportation> getResourceTransportationsFromWarehouse(Integer warehouseId);
    List<ResourceTransportation> getResourceTransportationsToWarehouse(Integer warehouseId);
    List<ResourceTransportation> getResourceTransportationsFromWarehouse(
            Integer warehouseId, Timestamp from, Timestamp to
    );
    List<ResourceTransportation> getResourceTransportationsToWarehouse(
            Integer warehouseId, Timestamp from, Timestamp to
    );
}

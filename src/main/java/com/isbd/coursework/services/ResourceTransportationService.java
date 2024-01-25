package com.isbd.coursework.services;

import com.isbd.coursework.api.ResourceTransportationApi;
import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.ResourceTransportation;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceTransportationService implements ResourceTransportationApi {

    private final Connection db;

    ResourceTransportationService(DbConnection db) {
        this.db = db.getConnection();
    }

    @Override
    public List<ResourceTransportation> getResourceTransportationsFromWarehouse(Integer warehouseId) {
        if (warehouseId == 0) return null;
        String selectStatement = "SELECT * FROM resource_transportation WHERE from_warehouse_id=?;";
        return getResourceTransportations(warehouseId, selectStatement);
    }

    @Override
    public List<ResourceTransportation> getResourceTransportationsToWarehouse(Integer warehouseId) {
        if (warehouseId == 0) return null;
        String selectStatement = "SELECT * FROM resource_transportation WHERE to_warehouse_id=?;";
        return getResourceTransportations(warehouseId, selectStatement);
    }

    @Override
    public List<ResourceTransportation> getResourceTransportationsFromWarehouse(Integer warehouseId, Timestamp from, Timestamp to) {
        if (warehouseId == 0) return null;
        String selectStatement = "SELECT * FROM resource_transportation WHERE from_warehouse_id=? " +
                "AND start_at > ? AND finish_at < ?;";
        return getResourceTransportationsWithTime(warehouseId, from, to, selectStatement);
    }

    @Override
    public List<ResourceTransportation> getResourceTransportationsToWarehouse(Integer warehouseId, Timestamp from, Timestamp to) {
        if (warehouseId == 0) return null;
        String selectStatement = "SELECT * FROM resource_transportation WHERE to_warehouse_id=? " +
                "AND start_at > ? AND finish_at < ?;";
        return getResourceTransportationsWithTime(warehouseId, from, to, selectStatement);
    }

    private List<ResourceTransportation> getResourceTransportationsWithTime(Integer warehouseId, Timestamp from, Timestamp to, String selectStatement) {
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, warehouseId);
            st.setTimestamp(2, from);
            st.setTimestamp(3, to);
            List<ResourceTransportation> warehouses = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                warehouses.add(ResourceTransportation.fromSet(set));
            }
            return warehouses;
        } catch (SQLException e) {
            return null;
        }
    }

    private List<ResourceTransportation> getResourceTransportations(Integer warehouseId, String selectStatement) {
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, warehouseId);
            List<ResourceTransportation> warehouses = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                warehouses.add(ResourceTransportation.fromSet(set));
            }
            return warehouses;
        } catch (SQLException e) {
            return null;
        }
    }
}

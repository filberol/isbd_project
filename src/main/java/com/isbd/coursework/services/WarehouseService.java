package com.isbd.coursework.services;

import com.isbd.coursework.api.WarehouseApi;
import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.Warehouse;
import com.isbd.coursework.entities.WarehouseResourceAllocation;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService implements WarehouseApi {

    private final Connection db;

    WarehouseService(DbConnection db) {
        this.db = db.getConnection();
    }

    @Override
    public Warehouse getWarehouseByStationId(Integer stationId) {
        if (stationId == 0) return null;
        String selectStatement = "SELECT * FROM warehouse WHERE station_id=?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, stationId);
            ResultSet set = st.executeQuery();
            set.next();
            return Warehouse.fromSet(set);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<WarehouseResourceAllocation> getResourceAllocations(Integer warehouseId) {
        if (warehouseId == 0) return null;
        String selectStatement = "SELECT * FROM warehouse_resource_allocation WHERE warehouse_id=? order by allocated_at;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, warehouseId);
            List<WarehouseResourceAllocation> allocations = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                allocations.add(WarehouseResourceAllocation.fromSet(set));
            }
            return allocations;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<WarehouseResourceAllocation> getResourceAllocationsBetween(Integer warehouseId, Timestamp from, Timestamp to) {
        if (warehouseId == 0) return null;
        String selectStatement = "SELECT * FROM warehouse_resource_allocation WHERE warehouse_id=? " +
                "AND allocated_at > ? AND allocated_at < ?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, warehouseId);
            st.setTimestamp(2, from);
            st.setTimestamp(3, to);
            List<WarehouseResourceAllocation> allocations = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                allocations.add(WarehouseResourceAllocation.fromSet(set));
            }
            return allocations;
        } catch (SQLException e) {
            return null;
        }
    }
}

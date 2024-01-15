package com.isbd.coursework.processes;

import com.isbd.coursework.database.DbConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;

@Controller
@RequestMapping("/brigade/resources")
public class ResourceAllocationProcesses {

    private final Connection db;

    ResourceAllocationProcesses(DbConnection db) {
        this.db = db.getConnection();
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_BRIGADE"})
    public ResponseEntity<String> newWarehouseResourceAllocation(
            @RequestParam String stationName,
            @RequestParam Integer resourcesKm,
            @RequestParam Timestamp allocatedAt
    ) {
        String updateStatement =
                "insert into warehouse_resource_allocation(warehouse_id, resources_allocated_km, allocated_at) values (?, ?, ?);";
        String selectStatement =
                "select id from warehouse where station_id = (select id from railway_station where name = ?);";
        try {
            PreparedStatement selSt = db.prepareStatement(selectStatement);
            selSt.setString(1, stationName);
            ResultSet set = selSt.executeQuery();
            set.next();
            int warehouseId = set.getInt("id");
            PreparedStatement updSt = db.prepareStatement(updateStatement);
            updSt.setInt(1, warehouseId);
            updSt.setInt(2, resourcesKm);
            updSt.setTimestamp(3, allocatedAt);
            updSt.executeUpdate();
            System.out.println("Allocated resources for " + stationName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

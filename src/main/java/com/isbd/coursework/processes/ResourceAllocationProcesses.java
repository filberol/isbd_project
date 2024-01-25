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
import java.time.LocalDateTime;

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
            @RequestParam LocalDateTime allocatedAt
    ) {
        try {
            CallableStatement selSt = db.prepareCall("{ call new_warehouse_resource_allocation(?,?,?)}");
            selSt.setString(1, stationName);
            selSt.setInt(2, resourcesKm);
            selSt.setTimestamp(3, Timestamp.valueOf(allocatedAt));
            selSt.execute();
            System.out.println("Allocated resources for " + stationName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

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
@RequestMapping("/admin/warehouse")
public class WarehouseProcesses {

    private final Connection db;

    WarehouseProcesses(DbConnection db) {
        this.db = db.getConnection();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> addWarehouse(
            @RequestParam String stationName
    ) {
        try {
            CallableStatement sel = db.prepareCall("{ call init_warehouse(?)}");
            sel.setString(1, stationName);
            sel.execute();
            System.out.println("Inserted warehouse for station " + stationName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

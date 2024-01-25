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
@RequestMapping("/admin/base")
public class RepairBaseProcesses {

    private final Connection db;

    RepairBaseProcesses(DbConnection db) {
        this.db = db.getConnection();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> addRepairBase(
            @RequestParam String stationName
    ) {
        try {
            CallableStatement insSt = db.prepareCall("{ call add_repair_base(?, 0, 0)}");
            insSt.setString(1, stationName);
            insSt.execute();
            System.out.println("Inserted repair base for station " + stationName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

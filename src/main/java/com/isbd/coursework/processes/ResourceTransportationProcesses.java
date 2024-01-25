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
@RequestMapping("/brigade/resources/transport")
public class ResourceTransportationProcesses {

    private final Connection db;

    ResourceTransportationProcesses(DbConnection db) {
        this.db = db.getConnection();
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_BRIGADE"})
    @RequestMapping("/start")
    public ResponseEntity<String> startResourceTransportation(
            @RequestParam String fromStation,
            @RequestParam String toStation,
            @RequestParam Integer resourceKm,
            @RequestParam LocalDateTime start
    ) {
        try {
            CallableStatement sel = db.prepareCall("{call start_resource_transportation(?,?,?,?)}");
            sel.setString(1, fromStation);
            sel.setString(2, toStation);
            sel.setInt(3, resourceKm);
            sel.setTimestamp(4, Timestamp.valueOf(start));

            sel.execute();
            System.out.println("Started transportation from " + fromStation);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_BRIGADE"})
    @RequestMapping("/finish")
    public ResponseEntity<String> finishResourceTransportation(
            @RequestParam Integer transportationId,
            @RequestParam LocalDateTime finish
    ) {

        try {
            CallableStatement updSt = db.prepareCall("{ call finish_resource_transportation(?,?)}");
            updSt.setInt(1, transportationId);
            updSt.setTimestamp(2, Timestamp.valueOf(finish));
            updSt.execute();
            System.out.println("Finished transportation " + transportationId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

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
            @RequestParam String start
    ) {
        String updateStatement =
                "insert into resource_transportation(from_warehouse_id, to_warehouse_id, start_at, resources_transportation_km) values (?, ?, ?, ?);";
        String selectStation =
                "select id from warehouse where station_id = (select id from railway_station where name = ?);";
        try {
            PreparedStatement selFrom = db.prepareStatement(selectStation);
            PreparedStatement selTo = db.prepareStatement(selectStation);
            selFrom.setString(1, fromStation);
            selTo.setString(1, toStation);
            ResultSet setFrom = selFrom.executeQuery();
            ResultSet setTo = selTo.executeQuery();
            setFrom.next();
            setTo.next();
            int fromId = setFrom.getInt("id");
            int toId = setTo.getInt("id");
            PreparedStatement updSt = db.prepareStatement(updateStatement);
            updSt.setInt(1, fromId);
            updSt.setInt(2, toId);
            updSt.setTimestamp(3, Timestamp.valueOf(start.replace("T"," ") + ":00"));
            updSt.setInt(4, resourceKm);
            updSt.executeUpdate();
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
            @RequestParam String finish
    ) {
        String updateStatement = "update resource_transportation set finish_at = ? where id = ?;";
        try {
            PreparedStatement updSt = db.prepareStatement(updateStatement);
            updSt.setTimestamp(1, Timestamp.valueOf(finish.replace("T"," ") + ":00"));
            updSt.setInt(2, transportationId);
            updSt.executeUpdate();
            System.out.println("Finished transportation " + transportationId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

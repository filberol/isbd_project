package com.isbd.coursework.processes;

import com.isbd.coursework.database.DbConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@RequestMapping("/admin/segment")
public class RailwaySegmentProcesses {

    private final Connection db;

    RailwaySegmentProcesses(DbConnection db) {
        this.db = db.getConnection();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @RequestMapping("/by_id")
    public ResponseEntity<String> addRailWaySegment(
            @RequestParam Integer fromRs,
            @RequestParam Integer toRs,
            @RequestParam Integer lengthKm
    ) {
        String insertStatement = "INSERT INTO railway_segment(from_rs, to_rs, length_km) VALUES (?, ?, ?);";
        try {
            PreparedStatement st = db.prepareStatement(insertStatement);
            st.setInt(1, fromRs);
            st.setInt(2, toRs);
            st.setInt(3, lengthKm);
            st.executeUpdate();
            st = db.prepareStatement(insertStatement);
            st.setInt(1, toRs);
            st.setInt(2, fromRs);
            st.setInt(3, lengthKm);
            st.executeUpdate();
            System.out.println("Inserted segment from " + fromRs);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @RequestMapping("/by_name")
    public ResponseEntity<String> addRailWaySegmentByName(
            @RequestParam String fromRs,
            @RequestParam String toRs,
            @RequestParam Integer lengthKm
    ) {
        String insertStatement = "INSERT INTO railway_segment(from_rs, to_rs, length_km) VALUES (?, ?, ?);";
        String selectStatement = "SELECT id FROM railway_station WHERE name = ?;";
        try {
            PreparedStatement from = db.prepareStatement(selectStatement);
            from.setString(1, fromRs);
            ResultSet fromSet = from.executeQuery();
            fromSet.next();
            int fromRsId = fromSet.getInt("id");
            PreparedStatement to = db.prepareStatement(selectStatement);
            to.setString(1, toRs);
            ResultSet toSet = to.executeQuery();
            toSet.next();
            int toRsId = toSet.getInt("id");
            PreparedStatement st1 = db.prepareStatement(insertStatement);
            st1.setInt(1, fromRsId);
            st1.setInt(2, toRsId);
            st1.setInt(3, lengthKm);
            st1.executeUpdate();
            PreparedStatement st2 = db.prepareStatement(insertStatement);
            st2.setInt(1, toRsId);
            st2.setInt(2, fromRsId);
            st2.setInt(3, lengthKm);
            st2.executeUpdate();
            System.out.println("Inserted segment from " + fromRs);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

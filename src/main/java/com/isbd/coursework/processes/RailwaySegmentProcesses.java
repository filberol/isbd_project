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
//        String insertStatement = "INSERT INTO railway_segment(from_rs, to_rs, length_km) VALUES (?, ?, ?);";
        try {
            CallableStatement st = db.prepareCall("{ call add_railway_segment(?,?,?)}");
            st.setInt(1, fromRs);
            st.setInt(2, toRs);
            st.setInt(3, lengthKm);
//            st.registerOutParameter(4, Types.NULL);
            st.execute();
//            st = db.prepareCall("{? = call add_railway_segment(?,?,?)}");
//            st.setInt(1, toRs);
//            st.setInt(2, fromRs);
//            st.setInt(3, lengthKm);
//            st.registerOutParameter(4, Types.NULL);
//            st.executeUpdate();
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

        try {
            CallableStatement st = db.prepareCall("{call add_railway_segment(?,?,?)}");
            st.setString(1, fromRs);
            st.setString(2, toRs);
            st.setInt(3, lengthKm);
            st.execute();
            System.out.println("Inserted segment from " + fromRs);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

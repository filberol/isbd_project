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
@RequestMapping("/admin/base")
public class RepairBaseProcesses {

    private final Connection db;

    RepairBaseProcesses(DbConnection db) {
        this.db = db.getConnection();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> addRepairBase(@RequestParam String stationName) {
        String insertStatement =
                "insert into repair_base(station_id, size_teams, curr_teams_hosted) values (?, 0, 0);";
        String selectStatement = "select id from railway_station where name = ?;";
        try {
            PreparedStatement sel = db.prepareStatement(selectStatement);
            sel.setString(1, stationName);
            ResultSet set = sel.executeQuery();
            set.next();
            int id = set.getInt("id");
            PreparedStatement insSt = db.prepareStatement(insertStatement);
            insSt.setInt(1, id);
            insSt.executeUpdate();
            System.out.println("Inserted repair base for station " + stationName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

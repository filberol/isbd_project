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
@RequestMapping("/brigade/route")
public class RepairTeamRoutesProcesses {

    private final Connection db;

    RepairTeamRoutesProcesses(DbConnection db) {
        this.db = db.getConnection();
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_BRIGADE"})
    @RequestMapping("/appoint")
    public ResponseEntity<String> appointRouteForRepairTeam(
            @RequestParam Integer teamId,
            @RequestParam String fromStation,
            @RequestParam String toStation,
            @RequestParam LocalDateTime planAt
    ) {
        try {
            CallableStatement sel = db.prepareCall("{call appoint_route_for_repair_team(?,?,?,?)}");
            sel.setInt(1, teamId);
            sel.setString(2, fromStation);
            sel.setString(3, toStation);
            sel.setTimestamp(4, Timestamp.valueOf(planAt));
            sel.execute();
            System.out.println("Planned route from " + fromStation);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_BRIGADE"})
    @RequestMapping("/finish")
    public ResponseEntity<String> finishRepairTeamRoute(
            @RequestParam Integer teamId,
            @RequestParam LocalDateTime arrived
    ) {
        try {
            CallableStatement sel = db.prepareCall("{? =  call finish_repair_team_route(?,?)}");
            sel.setInt(1, teamId);
            sel.setTimestamp(2, Timestamp.valueOf(arrived));
            sel.registerOutParameter(3, Types.INTEGER);
            int routeId = sel.executeUpdate();
            System.out.println("Finished route " + routeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_BRIGADE"})
    @RequestMapping("/start")
    public ResponseEntity<String> startRepairTeamRoute(
            @RequestParam Integer teamId,
            @RequestParam LocalDateTime departed
    ) {
        try {
            CallableStatement sel = db.prepareCall("{? = call start_repair_team_route(?,?)}");
            sel.setInt(1, teamId);
            sel.setTimestamp(2, Timestamp.valueOf(departed));
            sel.registerOutParameter(3, Types.INTEGER);
            int routeId = sel.executeUpdate();
            System.out.println("Started route " + routeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

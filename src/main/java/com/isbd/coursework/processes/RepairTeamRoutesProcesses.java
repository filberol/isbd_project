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
            @RequestParam String planAt
    ) {
        String insertStatement =
                "insert into repair_team_route(repair_team_id, from_base_id, to_base_id) values (?, ?, ?);";
        String insertStatement2 =
                "insert into repair_team_route_schedule(route_id, planned_at, departed_at, arrived_at) values (?, ?, null, null);";
        String selectStation =
                "select id from repair_base where station_id = (select id from railway_station where name = ?);";
        String selectStatement2 =
                "select max(id) from repair_team_route where repair_team_id = ? and from_base_id = ?;";
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
            PreparedStatement updSt = db.prepareStatement(insertStatement);
            updSt.setInt(1, teamId);
            updSt.setInt(2, fromId);
            updSt.setInt(3, toId);
            updSt.executeUpdate();
            PreparedStatement findSt = db.prepareStatement(selectStatement2);
            findSt.setInt(1, teamId);
            findSt.setInt(2, fromId);
            ResultSet findSet = findSt.executeQuery();
            findSet.next();
            int routeId = findSet.getInt("max");
            PreparedStatement insSt = db.prepareStatement(insertStatement2);
            insSt.setInt(1, routeId);
            insSt.setTimestamp(2, Timestamp.valueOf(planAt.replace("T"," ")));
            insSt.executeUpdate();
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
            @RequestParam String arrived
    ) {
        String insertStatement =
                "update repair_team_route_schedule set arrived_at = ? where route_id = ?;";
        String selectStatement =
                "select id from repair_team_route where repair_team_id = ?;";
        try {
            PreparedStatement selFrom = db.prepareStatement(selectStatement);
            selFrom.setInt(1, teamId);
            ResultSet setFrom = selFrom.executeQuery();
            setFrom.next();
            int routeId = setFrom.getInt("id");
            PreparedStatement updSt = db.prepareStatement(insertStatement);
            updSt.setTimestamp(1, Timestamp.valueOf(arrived.replace("T"," ")));
            updSt.setInt(2, routeId);
            updSt.executeUpdate();
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
            @RequestParam String departed
    ) {
        String insertStatement =
                "update repair_team_route_schedule set departed_at = ? where route_id = ?;";
        String selectStatement =
                "select id from repair_team_route where repair_team_id = ?;";
        try {
            PreparedStatement selFrom = db.prepareStatement(selectStatement);
            selFrom.setInt(1, teamId);
            ResultSet setFrom = selFrom.executeQuery();
            setFrom.next();
            int routeId = setFrom.getInt("id");
            PreparedStatement updSt = db.prepareStatement(insertStatement);
            updSt.setTimestamp(1, Timestamp.valueOf(departed.replace("T"," ")));
            updSt.setInt(2, routeId);
            updSt.executeUpdate();
            System.out.println("Started route " + routeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

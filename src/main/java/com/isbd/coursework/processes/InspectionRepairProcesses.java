package com.isbd.coursework.processes;

import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.enums.FaultClass;
import com.isbd.coursework.entities.enums.SiteVisitType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Controller
@RequestMapping("/brigade/route_site")
public class InspectionRepairProcesses {

    private final Connection db;

    InspectionRepairProcesses(DbConnection db) {
        this.db = db.getConnection();
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_BRIGADE"})
    @RequestMapping("/fixation")
    public ResponseEntity<String> addSiteFaultFixation(
            @RequestParam Integer segFaultId,
            @RequestParam Integer routeId,
            @RequestParam String found,
            @RequestParam String faultClass
    ) {
        String updateStatement =
                "insert into site_fault_fixation (segment_fault_id, route_id, found_at, fault_class) values (?, ?, ?, ?);";
        try {
            PreparedStatement updSt = db.prepareStatement(updateStatement);
            updSt.setInt(1, segFaultId);
            updSt.setInt(2, routeId);
            updSt.setTimestamp(3, Timestamp.valueOf(found.replace("T"," ")));
            updSt.setString(4, FaultClass.valueOf(faultClass).name());
            updSt.executeUpdate();
            System.out.println("Add fault fixation for " + segFaultId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_BRIGADE"})
    @RequestMapping("/site")
    public ResponseEntity<String> addInspectionRepairSite(
            @RequestParam Integer routeId,
            @RequestParam Integer rwSegId,
            @RequestParam Integer positionKm,
            @RequestParam String arrivedAt,
            @RequestParam String typeSite
    ) {
        String updateStatement =
                "insert into inspection_repair_site(route_id, railway_segment_id, position_point_km, arrived_at, type_site_action) values (?, ?, ?, ?, ?);";
        try {
            PreparedStatement updSt = db.prepareStatement(updateStatement);
            updSt.setInt(1, routeId);
            updSt.setInt(2, rwSegId);
            updSt.setInt(3, positionKm);
            updSt.setTimestamp(4, Timestamp.valueOf(arrivedAt.replace("T"," ")));
            updSt.setString(5, SiteVisitType.valueOf(typeSite).name());
            updSt.executeUpdate();
            System.out.println("Add site for route " + routeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

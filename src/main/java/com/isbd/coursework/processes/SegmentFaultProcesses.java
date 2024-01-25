package com.isbd.coursework.processes;

import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.enums.FaultClass;
import com.isbd.coursework.entities.enums.FaultStatus;
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
@RequestMapping("/brigade/fault")
public class SegmentFaultProcesses {

    private final Connection db;
    private final InspectionRepairProcesses inspectionRepairProcesses;

    SegmentFaultProcesses(
            DbConnection db,
            InspectionRepairProcesses inspectionRepairProcesses
    ) {
        this.db = db.getConnection();
        this.inspectionRepairProcesses = inspectionRepairProcesses;
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_BRIGADE"})
    @RequestMapping("/add")
    public ResponseEntity<String> addSegmentFault(
            @RequestParam Integer railwaySegmentId,
            @RequestParam String faultClass,
            @RequestParam Integer positionKm,
            @RequestParam String faultStatus
    ) {

        try {
            CallableStatement updSt = db.prepareCall("{ call add_segment_fault(?,?,?,?)}");
            updSt.setInt(1, railwaySegmentId);
            updSt.setString(2, FaultClass.valueOf(faultClass).name());
            updSt.setInt(3, positionKm);
            updSt.setString(4, FaultStatus.valueOf(faultStatus).name());
            updSt.execute();
            System.out.println("Add fault for " + railwaySegmentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_BRIGADE"})
    @RequestMapping("/add_on_route")
    public ResponseEntity<String> addSegmentFaultOnRoute(
            @RequestParam Integer railwaySegmentId,
            @RequestParam String faultClass,
            @RequestParam Integer positionKm,
            @RequestParam Integer routeId,
            @RequestParam LocalDateTime found,
            @RequestParam String faultStatus
    ) {
        ResponseEntity<String> res = addSegmentFault(railwaySegmentId, faultClass, positionKm, faultStatus);
        if (res.getStatusCode() == HttpStatus.BAD_REQUEST) return res;
//        String selectStatement =
//                "select max(id) from segment_fault where rw_seg_id = ? and position_km = ?;";
        try {
            CallableStatement findSt = db.prepareCall("{? = call get_id_segment_fault(?,?)}");
            findSt.setInt(1, railwaySegmentId);
            findSt.setInt(2, positionKm);
            findSt.registerOutParameter(3, Types.INTEGER);
            ResultSet findSet = findSt.executeQuery();
            findSet.next();
            int faultId = findSet.getInt("id");
            ResponseEntity<String> res2 =
                    inspectionRepairProcesses.addSiteFaultFixation(faultId, routeId, found, faultClass);
            if (res2.getStatusCode() == HttpStatus.BAD_REQUEST) return res2;
            System.out.println("Add fault and fixation fo route " + routeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_BRIGADE"})
    @RequestMapping("/change")
    public ResponseEntity<String> addSegmentFaultOnRoute(
            @RequestParam Integer segFaultId,
            @RequestParam String faultStatus
    ) {
//        String selectStatement =
//                "update segment_fault set fault_status=? where id = ?;";
        try {
            CallableStatement findSt = db.prepareCall("{ call change_fault_status(?,?)}");
            findSt.setInt(1, segFaultId);
            findSt.setString(2, FaultStatus.valueOf(faultStatus).name());
            findSt.execute();
            System.out.println("Update fault status of " + segFaultId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

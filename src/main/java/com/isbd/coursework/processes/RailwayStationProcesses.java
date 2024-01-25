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
@RequestMapping("/admin/railway")
public class RailwayStationProcesses {

    private final Connection db;
    private final RepairBaseProcesses repairBaseProcesses;
    private final WarehouseProcesses warehouseProcesses;
    private final RailwaySegmentProcesses railwaySegmentProcesses;

    RailwayStationProcesses(
            DbConnection db,
            RepairBaseProcesses repairBaseProcesses,
            WarehouseProcesses warehouseProcesses,
            RailwaySegmentProcesses railwaySegmentProcesses
    ) {
        this.db = db.getConnection();
        this.repairBaseProcesses = repairBaseProcesses;
        this.warehouseProcesses = warehouseProcesses;
        this.railwaySegmentProcesses = railwaySegmentProcesses;
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @RequestMapping("/by_id")
    public ResponseEntity<String> addRailwayStation(
            @RequestParam String name,
            @RequestParam Integer ownerId
    ) {
        try {
            CallableStatement st = db.prepareCall("{call add_railway_station(?,?)}");
            st.setString(1, name);
            st.setInt(2, ownerId);
            st.execute();
            System.out.println("Inserted rw station name " + name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @RequestMapping("/by_name")
    public ResponseEntity<String> addRailwayStationByName(
            @RequestParam String name,
            @RequestParam String ownerName
    ) {
        try {
            CallableStatement insSt = db.prepareCall("{call add_railway_station(?,?)}");
            insSt.setString(1, name);
            insSt.setString(2, ownerName);
            insSt.execute();
            System.out.println("Inserted rw station name " + name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @RequestMapping("/full")
    public ResponseEntity<String> addRailwayStationAndConnect(
            @RequestParam String stationName,
            @RequestParam String ownerName,
            @RequestParam String toStation,
            @RequestParam Integer lengthKm
    ) {
        ResponseEntity<String> addStation = addRailwayStationByName(stationName, ownerName);
        if (addStation.getStatusCode() == HttpStatus.BAD_REQUEST) return addStation;
        ResponseEntity<String> addSegment =
                railwaySegmentProcesses.addRailWaySegmentByName(stationName, toStation, lengthKm);
        if (addSegment.getStatusCode() == HttpStatus.BAD_REQUEST) return addSegment;
        ResponseEntity<String> addWarehouse =
                warehouseProcesses.addWarehouse(stationName);
        if (addWarehouse.getStatusCode() == HttpStatus.BAD_REQUEST) return addWarehouse;
        ResponseEntity<String> addBase =
                repairBaseProcesses.addRepairBase(stationName);
        if (addBase.getStatusCode() == HttpStatus.BAD_REQUEST) return addBase;
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.isbd.coursework.services;

import com.isbd.coursework.api.RepairBaseApi;
import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.RepairBase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class RepairBaseService implements RepairBaseApi {

    private final Connection db;

    RepairBaseService(DbConnection db) {
        this.db = db.getConnection();
    }

    @Override
    public RepairBase getRepairBaseByStationId(Integer stationId) {
        if (stationId == 0) return null;
        String selectStatement = "SELECT * FROM repair_base WHERE station_id=?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, stationId);
            ResultSet set = st.executeQuery();
            set.next();
            return RepairBase.fromSet(set);
        } catch (SQLException e) {
            return null;
        }
    }
}

package com.isbd.coursework.services;

import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.RailwaySegment;
import com.isbd.coursework.entities.RailwayStation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RailwayStationService {

    private final Connection db;

    RailwayStationService(DbConnection db) {
        this.db = db.getConnection();
    }

    public List<RailwayStation> getAllStations() {
        return getAllStations(10);
    }

    public List<RailwayStation> getAllStations(Integer limit) {
        if (limit == 0) return null;
        String selectStatement = "SELECT * FROM railway_station LIMIT ?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, limit);
            List<RailwayStation> stations = new ArrayList<>(limit);
            ResultSet set = st.executeQuery();
            while (set.next()) {
                stations.add(RailwayStation.fromSet(set));
            }
            return stations;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<RailwayStation> getStationNameLike(String name) {
        if (name == null) return null;
        String selectStatement = "SELECT * FROM railway_station WHERE name LIKE ?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setString(1, name);
            List<RailwayStation> stations = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                stations.add(RailwayStation.fromSet(set));
            }
            return stations;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<RailwaySegment> getSegmentsRelated(Integer id) {
        if (id == 0) return null;
        String selectStatement = "SELECT * FROM railway_segment WHERE from_rs=?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, id);
            List<RailwaySegment> ids = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                ids.add(RailwaySegment.fromSet(set));
            }
            return ids;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<RailwayStation> getStationsRelated(Integer id) {
        if (id == 0) return null;
        String selectStatement = "SELECT * FROM railway_station WHERE id IN" +
                "(SELECT id FROM railway_segment WHERE from_rs=?);";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, id);
            List<RailwayStation> ids = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                ids.add(RailwayStation.fromSet(set));
            }
            return ids;
        } catch (SQLException e) {
            return null;
        }
    }
}

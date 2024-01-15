package com.isbd.coursework.services;

import com.isbd.coursework.api.RepairTeamRouteScheduleApi;
import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.RepairTeamRouteSchedule;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepairTeamRouteScheduleService implements RepairTeamRouteScheduleApi {

    private final Connection db;

    RepairTeamRouteScheduleService(DbConnection db) {
        this.db = db.getConnection();
    }

    @Override
    public RepairTeamRouteSchedule getScheduleForRoute(Integer routeId) {
        if (routeId == 0) return null;
        String selectStatement = "SELECT * FROM repair_team_route_schedule WHERE route_id=?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, routeId);
            ResultSet set = st.executeQuery();
            return RepairTeamRouteSchedule.fromSet(set);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<RepairTeamRouteSchedule> getSchedulesBetween(Timestamp from, Timestamp to) {
        String selectStatement = "SELECT * FROM repair_team_route_schedule WHERE " +
                "planned_at > ? AND planned_at < ?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setTimestamp(1, from);
            st.setTimestamp(2, to);
            List<RepairTeamRouteSchedule> schedules = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                schedules.add(RepairTeamRouteSchedule.fromSet(set));
            }
            return schedules;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<RepairTeamRouteSchedule> getSchedulesForRouteBetween(Integer routeId, Timestamp from, Timestamp to) {
        String selectStatement = "SELECT * FROM repair_team_route_schedule WHERE route_id=? " +
                "AND planned_at > ? AND planned_at < ?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, routeId);
            st.setTimestamp(2, from);
            st.setTimestamp(3, to);
            List<RepairTeamRouteSchedule> schedules = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                schedules.add(RepairTeamRouteSchedule.fromSet(set));
            }
            return schedules;
        } catch (SQLException e) {
            return null;
        }
    }
}

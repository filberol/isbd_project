package com.isbd.coursework.services;

import com.isbd.coursework.api.InspectionRepairSiteApi;
import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.InspectionRepairSite;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InspectionRepairSiteService implements InspectionRepairSiteApi {

    private final Connection db;

    InspectionRepairSiteService(DbConnection db) {
        this.db = db.getConnection();
    }

    @Override
    public List<InspectionRepairSite> getInspectionsByRouteId(Integer routeId) {
        if (routeId == 0) return null;
        String selectStatement = "SELECT * FROM inspection_repair_site WHERE route_id=? " +
                "AND type_site_action='inspection'";
        return selectSitesByQueryAndRouteId(selectStatement, routeId);
    }

    @Override
    public List<InspectionRepairSite> getRepairsByRouteId(Integer routeId) {
        if (routeId == 0) return null;
        String selectStatement = "SELECT * FROM inspection_repair_site WHERE route_id=? " +
                "AND type_site_action='repair'";
        return selectSitesByQueryAndRouteId(selectStatement, routeId);
    }

    @Override
    public List<InspectionRepairSite> getRepairsByRailwaySegmentId(Integer segmentId) {
        if (segmentId == 0) return null;
        String selectStatement = "SELECT * FROM inspection_repair_site WHERE railway_segment_id=?;";
        return selectSitesByQueryAndRouteId(selectStatement, segmentId);
    }

    private List<InspectionRepairSite> selectSitesByQueryAndRouteId(String query, Integer routeId) {
        try {
            PreparedStatement st = db.prepareStatement(query);
            st.setInt(1, routeId);
            List<InspectionRepairSite> sites = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                sites.add(InspectionRepairSite.fromSet(set));
            }
            return sites;
        } catch (SQLException e) {
            return null;
        }
    }
}

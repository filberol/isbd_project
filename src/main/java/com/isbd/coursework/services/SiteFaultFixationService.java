package com.isbd.coursework.services;

import com.isbd.coursework.api.SiteFaultFixationApi;
import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.SiteFaultFixation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SiteFaultFixationService implements SiteFaultFixationApi {

    private final Connection db;

    SiteFaultFixationService(DbConnection db) {
        this.db = db.getConnection();
    }

    @Override
    public List<SiteFaultFixation> getFixationsByRouteId(Integer routeId) {
        if (routeId == 0) return null;
        String selectStatement = "SELECT * FROM site_fault_fixation WHERE route_id=? order by found_at desc;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, routeId);
            List<SiteFaultFixation> fixations = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                fixations.add(SiteFaultFixation.fromSet(set));
            }
            return fixations;
        } catch (SQLException e) {
            return null;
        }
    }
}

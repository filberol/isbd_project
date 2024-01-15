package com.isbd.coursework.services;

import com.isbd.coursework.api.RepairTeamRoutesApi;
import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.RepairTeamRoute;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepairTeamRoutesService implements RepairTeamRoutesApi {

    private final Connection db;

    RepairTeamRoutesService(DbConnection db) {
        this.db = db.getConnection();
    }

    @Override
    public List<RepairTeamRoute> getTeamRoutesByTeamId(Integer teamId) {
        if (teamId == 0) return null;
        String selectStatement = "SELECT * FROM repair_team_route WHERE repair_team_id=?;";
        return getRepairTeamRoutesByQuery(teamId, selectStatement);
    }

    @Override
    public List<RepairTeamRoute> getTeamRoutesFromBase(Integer baseId) {
        if (baseId == 0) return null;
        String selectStatement = "SELECT * FROM repair_team_route WHERE from_base_id=?;";
        return getRepairTeamRoutesByQuery(baseId, selectStatement);
    }

    @Override
    public List<RepairTeamRoute> getTeamRoutesToBase(Integer baseId) {
        if (baseId == 0) return null;
        String selectStatement = "SELECT * FROM repair_team_route WHERE to_base_id=?;";
        return getRepairTeamRoutesByQuery(baseId, selectStatement);
    }

    private List<RepairTeamRoute> getRepairTeamRoutesByQuery(Integer baseId, String selectStatement) {
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, baseId);
            List<RepairTeamRoute> routes = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                routes.add(RepairTeamRoute.fromSet(set));
            }
            return routes;
        } catch (SQLException e) {
            return null;
        }
    }
}

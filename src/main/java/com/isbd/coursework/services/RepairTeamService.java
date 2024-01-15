package com.isbd.coursework.services;

import com.isbd.coursework.api.RepairTeamApi;
import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.RepairTeamMember;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepairTeamService implements RepairTeamApi {

    private final Connection db;

    RepairTeamService(DbConnection db) {
        this.db = db.getConnection();
    }

    @Override
    public List<RepairTeamMember> getRepairTeamMembersById(Integer teamId) {
        if (teamId == 0) return null;
        String selectStatement = "SELECT * FROM repair_team_member where repair_team_id=?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, teamId);
            List<RepairTeamMember> members = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                members.add(RepairTeamMember.fromSet(set));
            }
            return members;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public RepairTeamMember getTeamHeadById(Integer teamId) {
        if (teamId == 0) return null;
        String selectStatement = "SELECT * FROM repair_team_member where id=" +
                "(SELECT team_head_id FROM repair_team where id=?);";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, teamId);
            ResultSet set = st.executeQuery();
            return RepairTeamMember.fromSet(set);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<Integer> getTeamIdsByOwner(Integer ownerId) {
        if (ownerId == 0) return null;
        String selectStatement = "SELECT * FROM repair_team where owner_id=?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, ownerId);
            List<Integer> members = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                members.add(set.getInt("id"));
            }
            return members;
        } catch (SQLException e) {
            return null;
        }
    }
}

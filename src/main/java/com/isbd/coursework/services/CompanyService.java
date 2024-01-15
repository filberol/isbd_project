package com.isbd.coursework.services;

import com.isbd.coursework.api.CompanyApi;
import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.Company;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CompanyService implements CompanyApi {

    private final Connection db;

    CompanyService(DbConnection db) {
        this.db = db.getConnection();
    }

    @Override
    public Company getCompanyById(Integer id) {
        if (id == 0) return null;
        String selectStatement = "SELECT * FROM company WHERE id=?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, id);
            ResultSet set = st.executeQuery();
            return Company.fromSet(set);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Company getCompanyByName(String name) {
        if (name == null) return null;
        String selectStatement = "SELECT * FROM company WHERE name=?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setString(1, name);
            ResultSet set = st.executeQuery();
            return Company.fromSet(set);
        } catch (SQLException e) {
            return null;
        }
    }
}

package com.isbd.coursework.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public record Company(
        Integer id,
        String name
) {
    public static Company fromSet(ResultSet set) throws SQLException {
        return new Company(
                set.getInt("id"),
                set.getString("name")
        );
    }
}

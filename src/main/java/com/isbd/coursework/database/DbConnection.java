package com.isbd.coursework.database;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DbConnection {

    private Connection connection;

    public DbConnection(PropConfig config) {
        try {
            System.out.println("Database url: " + config.url);
            this.connection = DriverManager.getConnection(config.url, config.username, config.password);
        } catch (SQLException e) {
            System.out.println("Cannot connect to database!");
            this.connection = null;
        }

    }

    public Connection getConnection() {
        return connection;
    }
}

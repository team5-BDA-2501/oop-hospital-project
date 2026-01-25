package com.company.data;

import com.company.data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    private final String url = "jdbc:postgresql://localhost:5432/somedb";  // Modify with your DB connection details
    private final String user = "postgres";  // Modify with your username
    private final String password = "Dilnaz2007";  // Modify with your password

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        // Optional: Implement closing the connection if necessary
    }
}
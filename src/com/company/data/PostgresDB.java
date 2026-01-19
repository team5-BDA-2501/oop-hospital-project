package com.company.data;

import com.company.data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB implements IDB {

    private final String url;
    private final String user;
    private final String password;
    private Connection connection;

    public PostgresDB(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, user, password);
            }
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("DB connection failed", e);
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (Exception ignored) {}
    }
}
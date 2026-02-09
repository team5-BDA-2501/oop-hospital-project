package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.repositories.interfaces.IDimTimeRepository;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.IsoFields;

public class DimTimeRepository implements IDimTimeRepository {
    private final IDB db;

    public DimTimeRepository(IDB db) {
        this.db = db;
    }

    @Override
    public int getOrCreateTimeId(LocalDate date) {
        String selectSql = "SELECT id FROM dim_time WHERE date = ?";
        String insertSql = "INSERT INTO dim_time(date, day_of_week, week_number, month, quarter, year) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection con = db.getConnection()) {

            try (PreparedStatement st = con.prepareStatement(selectSql)) {
                st.setDate(1, Date.valueOf(date));
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) return rs.getInt("id");
                }
            }

            String day = capitalize(date.getDayOfWeek().name().toLowerCase());
            int week = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
            int month = date.getMonthValue();
            int quarter = (month - 1) / 3 + 1;
            int year = date.getYear();

            try (PreparedStatement st = con.prepareStatement(insertSql)) {
                st.setDate(1, Date.valueOf(date));
                st.setString(2, day);
                st.setInt(3, week);
                st.setInt(4, month);
                st.setInt(5, quarter);
                st.setInt(6, year);

                try (ResultSet rs = st.executeQuery()) {
                    rs.next();
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return -1;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
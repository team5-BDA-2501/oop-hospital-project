package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Appointment;
import com.company.repositories.interfaces.IAppointmentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository implements IAppointmentRepository {

    private final IDB db;

    public AppointmentRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createAppointment(Appointment a) {
        String sql = """
            INSERT INTO appointments
            (user_id, doctor_id, availability_id, time_id,
             appointment_datetime, duration_minutes, status)
            VALUES (?, ?, ?, 1, ?, ?, ?)
            """;

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, a.getUserId());
            st.setInt(2, a.getDoctorId());
            st.setInt(3, a.getAvailabilityId());
            st.setTimestamp(4, Timestamp.valueOf(a.getDateTime()));
            st.setInt(5, a.getDurationMinutes());
            st.setString(6, a.getStatus());

            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Appointment> getAppointmentsByUser(int userId) {
        return fetch("WHERE user_id=?", userId);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        return fetch("WHERE doctor_id=?", doctorId);
    }

    private List<Appointment> fetch(String where, int id) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments " + where;

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new Appointment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("availability_id"),
                        rs.getTimestamp("appointment_datetime").toLocalDateTime(),
                        rs.getInt("duration_minutes"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return list;
    }
}
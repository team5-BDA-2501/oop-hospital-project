package com.company.repositories;

import com.company.repositories.interfaces.IAppointmentRepository;
import com.company.data.interfaces.IDB;
import com.company.models.Appointment;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository implements IAppointmentRepository {

    private final IDB db;

    public AppointmentRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (user_id, doctor_id, availability_id, time_id, " +
                "appointment_datetime, duration_minutes, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getUserId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setInt(3, appointment.getAvailabilityId());
            stmt.setInt(4, appointment.getTimeId());
            stmt.setTimestamp(5, Timestamp.valueOf(appointment.getAppointmentDatetime()));  // Use Timestamp to insert LocalDateTime
            stmt.setInt(6, appointment.getDurationMinutes());
            stmt.setString(7, appointment.getStatus());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error creating appointment: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Appointment> getAppointmentsByUser(int userId) {
        String sql = "SELECT * FROM appointments WHERE user_id = ?";
        List<Appointment> appointments = new ArrayList<>();

        try (Connection con = db.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("availability_id"),
                        rs.getInt("time_id"),
                        rs.getTimestamp("appointment_datetime").toLocalDateTime(), // Convert to LocalDateTime
                        rs.getInt("duration_minutes"),
                        rs.getString("status")
                );
                appointments.add(appointment);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching appointments for user: " + e.getMessage());
        }

        return appointments;
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        String sql = "SELECT * FROM appointments WHERE doctor_id = ?";
        List<Appointment> appointments = new ArrayList<>();

        try (Connection con = db.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("availability_id"),
                        rs.getInt("time_id"),
                        rs.getTimestamp("appointment_datetime").toLocalDateTime(), // Convert to LocalDateTime
                        rs.getInt("duration_minutes"),
                        rs.getString("status")
                );
                appointments.add(appointment);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching appointments for doctor: " + e.getMessage());
        }

        return appointments;
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        String sql = "DELETE FROM appointments WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, appointmentId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Return true if at least one row is deleted

        } catch (SQLException e) {
            System.out.println("Error cancelling appointment: " + e.getMessage());
        }

        return false;
    }

    public boolean hasOverlap(int doctorId, LocalDateTime start, LocalDateTime end) {
        String sql = "SELECT * FROM appointments WHERE doctor_id = ? AND appointment_datetime BETWEEN ? AND ?";
        try (Connection con = db.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, doctorId);
            stmt.setTimestamp(2, Timestamp.valueOf(start));
            stmt.setTimestamp(3, Timestamp.valueOf(end));

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // If we find any records, there's an overlap

        } catch (SQLException e) {
            System.out.println("Error checking for overlapping appointments: " + e.getMessage());
        }
        return false;
    }
}
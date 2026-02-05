package com.company.repositories;

import com.company.models.Appointment;
import com.company.models.AppointmentDetails;
import com.company.repositories.interfaces.IAppointmentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository implements IAppointmentRepository {
    private final Connection connection;

    public AppointmentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean createAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (user_id, doctor_id, availability_id, time_id, appointment_datetime, duration_minutes, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, appointment.getUserId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setInt(3, appointment.getAvailabilityId());
            stmt.setInt(4, appointment.getTimeId());
            stmt.setTimestamp(5, Timestamp.valueOf(appointment.getAppointmentDatetime()));
            stmt.setInt(6, appointment.getDurationMinutes());
            stmt.setString(7, appointment.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        String sql = "DELETE FROM appointments WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<AppointmentDetails> getAppointmentsByUser(int userId) {
        String sql = "SELECT * FROM appointments WHERE user_id = ?";
        List<AppointmentDetails> appointments = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(new AppointmentDetails(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("doctor_name"),
                        rs.getString("doctor_specialization"),  // Add specialization if needed
                        rs.getTimestamp("appointment_datetime").toLocalDateTime(),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }


    @Override
    public List<AppointmentDetails> getAppointmentsByDoctor(int doctorId) {
        String sql = "SELECT * FROM appointments WHERE doctor_id = ?";
        List<AppointmentDetails> appointments = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(new AppointmentDetails(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("doctor_name"),
                        rs.getString("doctor_specialization"),
                        rs.getTimestamp("appointment_datetime").toLocalDateTime(),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public List<AppointmentDetails> getAllAppointmentsDetails() {
        String sql = "SELECT appointments.id, users.name as user_name, doctors.first_name as doctor_name, " +
                "appointments.appointment_datetime, appointments.duration_minutes, appointments.status " +
                "FROM appointments " +
                "JOIN users ON appointments.user_id = users.id " +
                "JOIN doctors ON appointments.doctor_id = doctors.id";

        List<AppointmentDetails> appointments = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(new AppointmentDetails(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("doctor_name"),
                        rs.getString("doctor_specialization"),
                        rs.getTimestamp("appointment_datetime").toLocalDateTime(),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
}
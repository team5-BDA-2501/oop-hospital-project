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
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        String sql = "SELECT user_id, doctor_id, availability_id, time_id, appointment_datetime, duration_minutes, status " +
                "FROM appointments WHERE doctor_id = ? ORDER BY appointment_datetime";

        List<Appointment> appointments = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("user_id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("availability_id"),
                        rs.getInt("time_id"),
                        rs.getTimestamp("appointment_datetime").toLocalDateTime(),
                        rs.getInt("duration_minutes"),
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
        String sql =
                "SELECT a.id AS appointment_id, " +
                        "       u.name AS user_name, u.surname AS user_surname, " +
                        "       d.first_name AS doctor_first_name, d.last_name AS doctor_last_name, " +
                        "       d.specialization AS doctor_specialization, " +
                        "       a.appointment_datetime AS appointment_datetime, " +
                        "       a.status AS status " +
                        "FROM appointments a " +
                        "JOIN users u ON a.user_id = u.id " +
                        "JOIN doctors d ON a.doctor_id = d.id " +
                        "ORDER BY a.appointment_datetime";

        List<AppointmentDetails> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int appointmentId = rs.getInt("appointment_id");

                String userFullName = rs.getString("user_name") + " " + rs.getString("user_surname");
                String doctorFullName = rs.getString("doctor_first_name") + " " + rs.getString("doctor_last_name");
                String doctorSpec = rs.getString("doctor_specialization");

                list.add(new AppointmentDetails(
                        appointmentId,
                        userFullName,
                        doctorFullName,
                        doctorSpec,
                        rs.getTimestamp("appointment_datetime").toLocalDateTime(),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
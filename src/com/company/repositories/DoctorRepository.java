package com.company.repositories;

import com.company.models.Doctor;
import com.company.repositories.interfaces.IDoctorRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository implements IDoctorRepository {
    private final Connection connection;

    public DoctorRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Doctor getDoctorById(int doctorId) {
        String sql = "SELECT * FROM doctors WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Doctor(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("specialization"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBoolean("is_active")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        String sql = "SELECT * FROM doctors WHERE specialization = ?";
        List<Doctor> doctors = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, specialization);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("specialization"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }


    @Override
    public boolean addDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctors (first_name, last_name, specialization, email, phone, is_active, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getLastName());
            stmt.setString(3, doctor.getSpecialization());
            stmt.setString(4, doctor.getEmail());
            stmt.setString(5, doctor.getPhone());
            stmt.setBoolean(6, doctor.isActive());
            stmt.setString(7, doctor.getUsername());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean deleteDoctor(int doctorId) {
        String sql = "DELETE FROM doctors WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Add availability for a doctor
    @Override
    public boolean addAvailability(int doctorId, String dayOfWeek, String startTime, String endTime) {
        String sql = "INSERT INTO doctor_availability (doctor_id, day_of_week, start_time, end_time) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            java.time.LocalTime start = java.time.LocalTime.parse(startTime); // accepts HH:mm
            java.time.LocalTime end = java.time.LocalTime.parse(endTime);     // accepts HH:mm

            stmt.setInt(1, doctorId);
            stmt.setString(2, dayOfWeek);
            stmt.setTime(3, java.sql.Time.valueOf(start)); // convert correctly
            stmt.setTime(4, java.sql.Time.valueOf(end));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Invalid time format. Use HH:mm (example: 13:00).");
        }
        return false;
    }
}
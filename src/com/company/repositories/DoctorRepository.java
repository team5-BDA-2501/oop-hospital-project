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
            } else {
                System.out.println("Doctor not found with ID: " + doctorId);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching doctor by ID: " + e.getMessage());
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
                Doctor doctor = new Doctor(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("specialization"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBoolean("is_active")
                );
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching doctors by specialization: " + e.getMessage());
        }
        return doctors;
    }
}
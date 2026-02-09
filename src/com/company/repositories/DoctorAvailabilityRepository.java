package com.company.repositories;

import com.company.models.DoctorAvailability;
import com.company.repositories.interfaces.IDoctorAvailabilityRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorAvailabilityRepository implements IDoctorAvailabilityRepository {
    private final Connection connection;

    // Constructor to initialize the connection to the database
    public DoctorAvailabilityRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<DoctorAvailability> getAvailabilityByDoctor(int doctorId) {
        String query = "SELECT * FROM doctor_availability WHERE doctor_id = ?";
        List<DoctorAvailability> availabilityList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                DoctorAvailability availability = new DoctorAvailability(
                        rs.getInt("doctor_id"),
                        rs.getString("day_of_week"),
                        rs.getTime("start_time").toLocalTime(),
                        rs.getTime("end_time").toLocalTime()
                );

                availabilityList.add(availability);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availabilityList;
    }

    @Override
    public boolean addAvailability(int doctorId, String dayOfWeek, String startTime, String endTime) {
        String query = "INSERT INTO doctor_availability (doctor_id, day_of_week, start_time, end_time) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, doctorId);
            ps.setString(2, dayOfWeek);
            ps.setString(3, startTime);
            ps.setString(4, endTime);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.DoctorAvailability;
import com.company.repositories.interfaces.IDoctorAvailabilityRepository;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DoctorAvailabilityRepository implements IDoctorAvailabilityRepository {

    private final IDB db;

    public DoctorAvailabilityRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean add(DoctorAvailability a) {
        String sql = "INSERT INTO doctor_availability (doctor_id, day_of_week, start_time, end_time) VALUES (?,?,?,?)";
        try (PreparedStatement st = db.getConnection().prepareStatement(sql)) {
            st.setInt(1, a.getDoctorId());
            st.setString(2, a.getDayOfWeek());
            st.setTime(3, Time.valueOf(a.getStartTime()));
            st.setTime(4, Time.valueOf(a.getEndTime()));
            return st.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<DoctorAvailability> getByDoctor(int doctorId) {
        List<DoctorAvailability> list = new ArrayList<>();
        String sql = "SELECT * FROM doctor_availability WHERE doctor_id=?";
        try (PreparedStatement st = db.getConnection().prepareStatement(sql)) {
            st.setInt(1, doctorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new DoctorAvailability(
                        rs.getInt("id"),
                        doctorId,
                        rs.getString("day_of_week"),
                        rs.getTime("start_time").toLocalTime(),
                        rs.getTime("end_time").toLocalTime()
                ));
            }
        } catch (Exception ignored) {}
        return list;
    }
}
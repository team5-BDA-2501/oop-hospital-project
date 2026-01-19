package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Doctor;
import com.company.repositories.interfaces.IDoctorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository implements IDoctorRepository {
    private final IDB db;

    public DoctorRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctors(first_name, last_name, specialization, email, phone, is_active) " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, doctor.getFirstName());
            st.setString(2, doctor.getLastName());
            st.setString(3, doctor.getSpecialization());
            st.setString(4, doctor.getEmail());
            st.setString(5, doctor.getPhone());
            st.setBoolean(6, doctor.isActive());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Doctor getDoctor(int id) {
        String sql = "SELECT id, first_name, last_name, specialization, email, phone, is_active " +
                "FROM doctors WHERE id=?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        String sql = "SELECT id, first_name, last_name, specialization, email, phone, is_active " +
                "FROM doctors ORDER BY id";

        List<Doctor> list = new ArrayList<>();

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(new Doctor(
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
            System.out.println("sql error: " + e.getMessage());
            return null;
        }
        return list;
    }
}
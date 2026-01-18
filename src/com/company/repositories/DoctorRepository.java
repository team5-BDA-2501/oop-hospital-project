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
        String sql = "INSERT INTO doctors(name, surname, gender, position) VALUES (?,?,?,?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, doctor.getName());
            st.setString(2, doctor.getSurname());
            st.setBoolean(3, doctor.getGender());
            st.setString(4, doctor.getPosition());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Doctor getDoctor(int id) {
        String sql = "SELECT id, name, surname, gender, position FROM doctors WHERE id=?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Doctor(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getBoolean("gender"),
                            rs.getString("position")
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
        String sql = "SELECT id, name, surname, gender, position FROM doctors ORDER BY id";
        List<Doctor> doctors = new ArrayList<>();

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getString("position")
                ));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return doctors;
    }
}

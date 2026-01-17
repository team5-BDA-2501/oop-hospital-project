package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Doctor;
import com.company.controllers.interfaces.IDoctorController;
import com.company.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository implements IDoctorController {
    private final IDB db;

    public DoctorRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createDoctor(Doctor doctor) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO doctors(name,surname,gender,position) VALUES (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, doctor.getName());
            st.setString(2, doctor.getSurname());
            st.setBoolean(3, doctor.getGender());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;

        @Override
        public Doctor getDoctor(int id) {
            Connection con = null;

            try {
                con = db.getConnection();
                String sql = "SELECT id,name,surname,gender,position FROM doctors WHERE id=?";
                PreparedStatement st = con.prepareStatement(sql);

                st.setInt(1, id);

                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    return new Doctor(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getBoolean("gender")),
                            rs.getString("position");
                }
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }

            return null;
        }

        @Override
        public List<Doctor> getAllDoctors() {
            Connection con = null;

            try {
                con = db.getConnection();
                String sql = "SELECT id,name,surname,gender,position FROM doctors";
                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery(sql);
                List<Doctor> doctors = new ArrayList<>();
                while (rs.next()) {
                    Doctor doctor = new Doctor(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getBoolean("gender")),
                            rs.getString("position");

                    doctors.add(doctor);
                }

                return doctors;
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }

            return null;
        }
    }
}

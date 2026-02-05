package com.company.repositories;

import com.company.models.Role;
import com.company.models.User;
import com.company.repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        String query = "SELECT id, name, surname, username, password, gender, role " +
                "FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    // ✅ Convert String role -> Role enum safely
                    String roleStr = rs.getString("role");
                    Role role = (roleStr == null) ? Role.USER : Role.valueOf(roleStr);

                    // ✅ users table DOES NOT have doctor_id, so pass null
                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getBoolean("gender"),
                            role,
                            null
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // if role in DB is wrong like "user" instead of "USER"
            System.out.println("Invalid role value in DB. Please use USER/ADMIN/DOCTOR exactly.");
        }

        return null;
    }

    @Override
    public boolean createUser(User user) {
        // ✅ If you have role column, insert it too.
        // If role column exists and is NOT NULL, you MUST insert it.
        String query = "INSERT INTO users (name, surname, username, password, gender, role) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setBoolean(5, user.getGender());

            // ✅ role is enum -> store as text
            Role role = user.getRole() == null ? Role.USER : user.getRole();
            ps.setString(6, role.name());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Registration failed.");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<User> getAllUsers() {
        // ✅ REMOVE doctor_id because it does not exist in users table
        String sql = "SELECT id, name, surname, username, password, gender, role FROM users";
        List<User> users = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String roleStr = rs.getString("role");
                Role role = (roleStr == null) ? Role.USER : Role.valueOf(roleStr);

                // ✅ no doctor_id column -> pass null
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("gender"),
                        role,
                        null
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role value in DB. Please use USER/ADMIN/DOCTOR exactly.");
        }

        return users;
    }
}
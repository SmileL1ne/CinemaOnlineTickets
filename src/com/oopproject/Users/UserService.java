package com.oopproject.Users;

import java.sql.*;

public class UserService {
    private final Connection connection;
    ;
    public UserService(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void addUser(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to add user: " + e.getMessage());
        }
    }

    public boolean userLogIn(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            System.err.println("Failed to log in user: " + e.getMessage());
            return false;
        }
    }

    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.executeUpdate();
        }
    }
}

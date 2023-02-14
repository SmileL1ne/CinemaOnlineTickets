package com.oopproject.Users;

import com.oopproject.DatabaseConnection.PostgresConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserService {

    private final UserConnection userCon;
    private PostgresConnection connection;
    public UserService(UserConnection userCon) {
        this.userCon = userCon;
    }

    public void registerUser() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please write you username: ");
        String user_username = in.nextLine();
        System.out.println("Please write your password: ");
        String user_password = in.nextLine();
        User user = new User(user_username, user_password);
        userCon.addUser(user);
    }

    public boolean userLogIn() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please write you username: ");
        String user_username = in.nextLine();
        System.out.println("Please write your password: ");
        String user_password = in.nextLine();
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.getConnection().prepareStatement(sql)) {
            statement.setString(1, user_username);
            statement.setString(2, user_password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Failed to log in user: " + e.getMessage());
            return false;
        }
    }
}

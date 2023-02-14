package com.oopproject.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserConnection {

    private final Connection connection;
    private static final String INSERT_USER_SQL = "INSERT INTO users (username, password) VALUES (?, ?)";

    public UserConnection(Connection connection){
        this.connection = connection;
    }

    public void addUser(User user) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.executeUpdate();
    }
}

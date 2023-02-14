package com.oopproject.DatabaseConnection;

import java.sql.*;

public class PostgresConnection {

    private Connection connection;
    public PostgresConnection(){
        try{
            String connectionUrl = "jdbc:postgresql://localhost:7777/cinema_booking";
            Connection connection = DriverManager.getConnection(connectionUrl, "postgres", "postgres");
        }catch (SQLException e){
            System.err.println("Failed to connect to database: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        String query = "select * from admins_list;";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            Class.forName("org.postgresql.Driver");

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                System.out.println(resultSet.getString("admin_username") + " " +
                        resultSet.getString("admin_password") + " " +
                        resultSet.getInt("admin_id"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public Connection getConnection(){
        return connection;
    }
}

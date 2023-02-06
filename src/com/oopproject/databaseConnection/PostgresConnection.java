package com.oopproject.databaseConnection;

import java.sql.*;

public class PostgresConnection {

    public static void main(String[] args) {
        String connectionUrl = "jdbc:postgresql://localhost:7777/cinema_booking";
        String query = "select * from admins_list;";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(connectionUrl, "postgres", "postgres");

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
}

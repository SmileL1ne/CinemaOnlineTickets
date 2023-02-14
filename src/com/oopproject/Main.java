package com.oopproject;

import com.oopproject.DatabaseConnection.PostgresConnection;
import com.oopproject.Users.User;
import com.oopproject.Users.UserConnection;
import com.oopproject.Users.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello and welcome to the cinema tickets online tickets!");
        System.out.println("What do you want to do? (Register/Log in): ");

        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();

        PostgresConnection postgresConnection = new PostgresConnection();
        Connection connection = postgresConnection.getConnection();
        UserConnection userConnection = new UserConnection(connection);
        UserService userService = new UserService(userConnection);


        if(answer.equals("Register")){
            userService.registerUser();
        }else if(answer.equals("Log in")){
            if(!userService.userLogIn()) System.out.println("Invalid inputs for username or password"); return;
        }else{
            System.out.println("Invalid input. Try again");
            return;
        }
    }
    public class User {
        private String username;
        private String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public class UserRegistry {
        private ArrayList<User> users;

        public UserRegistry() {
            this.users = new ArrayList<>();
        }

        public void registerUser(String username, String password) {
            User user = new User(username, password);
            users.add(user);
        }

        public boolean checkUsernameExists(String username) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return true;
                }
            }
            return false;
        }

        public boolean checkPasswordMatch(String username, String password) {
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            }
            return false;
        }
    }
}
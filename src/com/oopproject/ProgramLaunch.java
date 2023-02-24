package com.oopproject;

import com.oopproject.MovieBooking.Movie;
import com.oopproject.MovieBooking.MovieService;
import com.oopproject.Users.Admins;
import com.oopproject.Users.User;
import com.oopproject.Users.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProgramLaunch {
    public void launchProgram() throws SQLException {
        String connectionUrl = "jdbc:postgresql://localhost:7777/cinema_booking";
        Connection connection = DriverManager.getConnection(connectionUrl, "postgres", "postgres");
        MovieService movieService = new MovieService();
        try {
            UserService userService = new UserService(connection);
            Scanner scanner = new Scanner(System.in);
            boolean finished = false;
            while (!finished) {
                System.out.println("Welcome to Online Cinema Ticket Booking!");
                System.out.println("Please choose an option:");
                System.out.println("1. Log in");
                System.out.println("2. Register");
                System.out.println("3. Quit");
                System.out.println("Write your answer here: ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1" -> {
                        System.out.println("Enter your username:");
                        String username = scanner.nextLine();
                        System.out.println("Enter your password:");
                        String password = scanner.nextLine();
                        boolean isSucceed = userService.userLogIn(username, password);
                        if (username.equals(Admins.ADMIN_1_USERNAME) && password.equals(Admins.ADMIN_1_PASSWORD)){
                            boolean admin_finished = false;
                            System.out.println("You're successfully logged in as an Admin! So, what do you want to do?");
                            while(!admin_finished) {
                                System.out.println("1. Delete user");
                                System.out.println("2. Add a movie");
                                System.out.println("3. Quit admin mode");
                                String admin_choice = scanner.nextLine();
                                switch (admin_choice) {
                                    case "1" -> {
                                        System.out.println("Enter user's username: ");
                                        String username_delete = scanner.nextLine();
                                        userService.deleteUser(username_delete);
                                    }
                                    case "2" -> {
                                        System.out.println("Enter a name of the movie: ");
                                        String movie_name = scanner.nextLine();
                                        System.out.println("Enter a cost of the movie ticket: ");
                                        int movie_cost = scanner.nextInt();
                                        System.out.println("Enter a rating of the movie: ");
                                        int movie_rating = scanner.nextInt();
                                        System.out.println("Enter a duration of the movie: ");
                                        String movie_duration = scanner.next();
                                        System.out.println("Enter an amount of tickets for the movie: ");
                                        int movie_tickets = scanner.nextInt();
                                        Movie movie = new Movie(movie_cost, movie_name, movie_rating, movie_duration, movie_tickets);
                                        if(movieService.addMovie(movie)) System.out.println("Successfully added a movie");
                                        else System.out.println("Couldn't add a new movie");
                                    }
                                    case "3" -> {
                                        System.out.println("Quitting admin mode...");
                                        admin_finished = true;
                                    }
                                }
                            }
                        }
                        else if (isSucceed) {
                            System.out.println("Logged in successfully!");
                            finished = true;
                            boolean loggedActions = false;
                            System.out.println("So, what do you want to do?");
                            while (!loggedActions){
                                System.out.println("1. Get a list of available movies");
                                System.out.println("2. Buy a ticket for a movie");
                                System.out.println("3. Quit program");
                                String loggedChoice = scanner.next();
                                switch (loggedChoice){
                                    case "1" -> {
                                        System.out.println("Here is the list of currently available movies: ");
                                        List<Movie> movies = movieService.getAllMovies();
                                        movieService.printAllMovies(movies);
                                    }
                                    case "2" -> {
                                        System.out.println("Type a name of the movie: ");
                                        String movieName = scanner.next();
                                        if(movieService.isAccessible(movieName)) movieService.buyTicket(movieName);
                                        else System.out.println("Currently not available :(");
                                    }
                                    case "3" -> {
                                        System.out.println("Quitting the program...");
                                        loggedActions = true;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Login failed. Please try again.");
                        }
                    }
                    case "2" -> {
                        System.out.println("Enter your username:");
                        String newUsername = scanner.nextLine();
                        System.out.println("Enter your password:");
                        String newPassword = scanner.nextLine();
                        User newUser = new User(newUsername, newPassword);
                        userService.addUser(newUser);
                        System.out.println("Registered successfully! You can log in again to buy a ticket for a movie :)");
                        finished = true;
                    }
                    case "3" -> {
                        finished = true;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Invalid choice. Please try again");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
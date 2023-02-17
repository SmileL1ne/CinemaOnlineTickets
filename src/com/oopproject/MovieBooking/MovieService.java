package com.oopproject.MovieBooking;

import com.oopproject.DatabaseConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieService implements Bookable{
    private Connection connection;

    public MovieService(Connection connection) {
        this.connection = connection;
    }

    public List<MovieBooking> getAllMovies() throws SQLException {
        List<MovieBooking> movies = new ArrayList<>();
        String query = "SELECT * FROM movies";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int cost = resultSet.getInt("cost");
                int rating = resultSet.getInt("raing");
                String duration = resultSet.getString("duration");
                int tickets = resultSet.getInt("ticket_count");
                movies.add(new MovieBooking(cost, name, rating, duration, tickets));
            }
        }
        return movies;
    }

    @Override
    public boolean isBookable(String name) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "SELECT ticket_count FROM movies WHERE name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int ticketCount = rs.getInt("ticket_count");
                        return ticketCount > 0;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking if movie is bookable: " + e.getMessage());
        }
        return false;
    }

    public boolean addMovie(MovieBooking movie) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "INSERT INTO movies (title, director, release_date, ticket_count) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, movie.getName());
                stmt.setInt(2, movie.getCost());
                stmt.setInt(3, movie.getRating());
                stmt.setString(4, movie.getMovieDuration());
                stmt.setInt(5, movie.getTickets());
                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error adding new movie: " + e.getMessage());
        }
        return false;
    }
}

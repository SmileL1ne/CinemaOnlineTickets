package com.oopproject.MovieBooking;

import com.oopproject.CinemaSystems.CinemaSystems;
import com.oopproject.DatabaseConnection.PostgresConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class MovieBooking extends CinemaSystems {

    private String movieDuration;
    private int tickets;
    private Connection connection = PostgresConnection.getConnection();

    public MovieBooking(int cost, String name, int rating, String movieDuration, int tickets) throws SQLException {
        super(cost, name, rating);
        this.movieDuration = movieDuration;
        this.tickets = tickets;
    }

    @Override
    public boolean isAccessible(String name) {
        return false; // checking if name of film is exist in "Movies" table
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public int getTickets() {
        return tickets;
    }
}

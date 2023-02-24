package com.oopproject.MovieBooking;

import com.oopproject.CinemaSystems.CinemaSystems;

import java.sql.SQLException;

public class Movie extends CinemaSystems {

    private final String movieDuration;
    private final int tickets;

    public Movie(int cost, String name, int rating, String movieDuration, int tickets) throws SQLException {
        super(cost, name, rating, tickets);
        this.movieDuration = movieDuration;
        this.tickets = tickets;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public int getTickets() {
        return tickets;
    }


    @Override
    public String toString() {
        return "Name: '" + getName() + "' Duration: " + getMovieDuration() + " Rating: " + getRating()
                + " Cost: " + getCost() + " Available tickets count: " + getTickets();
    }

    @Override
    public boolean isAccessible(String name) {
        return false;
    }
}

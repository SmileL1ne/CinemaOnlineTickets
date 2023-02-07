package com.oopproject.MovieBooking;

import com.oopproject.CinemaSystems.CinemaSystems;

public class MovieBooking extends CinemaSystems implements Bookable {

    private String movieDuration;

    public MovieBooking(int cost, String name, int rating, String movieDuration) {
        super(cost, name, rating);
        this.movieDuration = movieDuration;
    }

    @Override
    public boolean isAccessible(String name) {
        return false; // checking if name of film is exist in "Movies" table
    }

    @Override
    public boolean isBookable(String name) {
        return false; // checking if there is any free seats in "Movies" table
    }

    public String getMovieDuration() {
        return movieDuration;
    }
}

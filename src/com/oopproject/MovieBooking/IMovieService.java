package com.oopproject.MovieBooking;

/*
    Methods for to interact with movies in database
 */

import java.sql.SQLException;
import java.util.List;

public interface IMovieService {
    boolean addMovie(Movie movie);

    List<Movie> getAllMovies() throws SQLException;
}

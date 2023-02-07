package com.oopproject.MovieBooking;

/*
    This interface is checking if there is any
    free seats to this movie
 */

public interface Bookable {

    boolean isBookable(String name);
}

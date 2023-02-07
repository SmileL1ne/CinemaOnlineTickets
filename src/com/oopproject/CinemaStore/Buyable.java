package com.oopproject.CinemaStore;

/*
    This interface checks if product is buyable by checking
    its left count in table "Products"
 */

public interface Buyable {

    boolean isBuyable(String name);
}

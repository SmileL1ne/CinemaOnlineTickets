package com.oopproject.CinemaStore;

import com.oopproject.CinemaSystems.CinemaSystems;

public class CinemaStoreProduct extends CinemaSystems implements Buyable {
    private String size;

    public CinemaStoreProduct(int cost, String name, int rating, String size) {
        super(cost, name, rating);
        this.size = size;
    }

    @Override
    public boolean isAccessible(String name) {
        return false; // checking if name of product exists in "Product" table
    }

    @Override
    public boolean isBuyable(String name) {
        return false; // checking if count of the product is not zero in table "Product"
    }

    public String getSize() {
        return size;
    }
}

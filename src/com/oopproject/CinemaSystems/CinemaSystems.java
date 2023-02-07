package com.oopproject.CinemaSystems;

public abstract class CinemaSystems implements Accessible, CSInterface {

    private int cost;
    private String name;
    private int rating;
    private int count;

    public CinemaSystems(int cost, String name, int rating) {
        this.cost = cost;
        this.name = name;
        this.rating = rating;
    }

    @Override
    public int overallCost() {
        return getCount() * getCost();
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getCount() {
        return count;
    }

}

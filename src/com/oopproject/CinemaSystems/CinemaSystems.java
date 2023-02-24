package com.oopproject.CinemaSystems;

public abstract class CinemaSystems implements Accessible {

    private final int cost;
    private final String name;
    private final int rating;
    private final int tickets;

    public CinemaSystems(int cost, String name, int rating, int tickets) {
        this.cost = cost;
        this.name = name;
        this.rating = rating;
        this.tickets = tickets;
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

    public int getTickets() {
        return tickets;
    }

}

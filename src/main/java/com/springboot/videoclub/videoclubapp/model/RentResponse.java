package com.springboot.videoclub.videoclubapp.model;

public class RentResponse {

    private int totalCost;
    private int loyaltyPoints;

    public RentResponse(int totalCost, int loyaltyPoints) {
        this.totalCost = totalCost;
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}

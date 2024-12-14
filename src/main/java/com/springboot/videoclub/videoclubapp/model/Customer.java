package com.springboot.videoclub.videoclubapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Customer {
    private String id;
    private String name;
    private int loyaltyPoints;

    public void addPoints(int points) {
        this.loyaltyPoints += points;
    }
}
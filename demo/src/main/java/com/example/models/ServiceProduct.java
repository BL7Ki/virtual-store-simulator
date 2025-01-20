package com.example.models;

public class ServiceProduct implements Product {
    private String name;
    private double price;
    private String description;
    private int durationInDays;

    public ServiceProduct(String name, double price, String description, int durationInDays) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.durationInDays = durationInDays;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationInDays() {
        return durationInDays;
    }
}

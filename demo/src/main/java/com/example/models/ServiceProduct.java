package com.example.models;

import java.util.Objects;

public class ServiceProduct implements Product {
    private final String name;
    private final double price;
    private final String description;
    private final int durationInDays;

    public ServiceProduct(String name, double price, String description, int durationInDays) {
        if (price < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        if (durationInDays < 0) {
            throw new IllegalArgumentException("A duração em dias não pode ser negativa.");
        }

        this.name = name;
        this.price = price;
        this.description = description;
        this.durationInDays = durationInDays;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    @Override
    public String toString() {
        return String.format("ServiceProduct{name='%s', price=%.2f, description='%s', durationInDays=%d}", 
                              name, price, description, durationInDays);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ServiceProduct that = (ServiceProduct) obj;
        return Double.compare(that.price, price) == 0 &&
               durationInDays == that.durationInDays &&
               name.equals(that.name) &&
               description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description, durationInDays);
    }
}

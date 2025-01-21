package com.example.models;

import java.util.Objects;

public class DigitalProduct implements Product {
    private final String name;
    private final double price;
    private final String description;

    public DigitalProduct(String name, double price, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode ser nula ou vazia.");
        }

        this.name = name;
        this.price = price;
        this.description = description;
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

    @Override
    public String toString() {
        return "DigitalProduct{" +
               "name='" + name + '\'' +
               ", price=" + price +
               ", description='" + description + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        DigitalProduct that = (DigitalProduct) obj;
        return Double.compare(that.price, price) == 0 &&
               name.equals(that.name) &&
               description.equals(that.description);
    }

    @Override
    public int hashCode() { // Eficiência em armazenamento e recuperação de dados. vai salvar como um inteiro
        return Objects.hash(name, price, description);
    }
}

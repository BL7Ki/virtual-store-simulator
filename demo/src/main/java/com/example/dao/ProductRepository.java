package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.models.Product;
import com.example.models.ServiceProduct;

public class ProductRepository {

    public void saveProduct(Product product) throws SQLException {
        String sql;

        // Determina a consulta SQL com base no tipo do produto
        if (product instanceof ServiceProduct) {
            sql = "INSERT INTO products (name, price, description, durationInDays) VALUES (?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO products (name, price, description) VALUES (?, ?, ?)";
        }

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());

            if (product instanceof ServiceProduct serviceProduct) {
                statement.setInt(4, serviceProduct.getDurationInDays());
            }

            statement.executeUpdate();

            // Log para sucesso
            System.out.println("Produto salvo com sucesso: " + product.getName());
        }
    }

    public void listProducts() throws SQLException {
        String sql = "SELECT * FROM products";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Conteúdo da tabela 'products':");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");

                int durationInDays = resultSet.getInt("durationInDays");
                if (resultSet.wasNull()) {
                    System.out.printf("Produto: %s, Preço: %.2f, Descrição: %s%n", name, price, description);
                } else {
                    System.out.printf("Produto de Serviço: %s, Preço: %.2f, Descrição: %s, Duração: %d dias%n",
                            name, price, description, durationInDays);
                }
            }
        }
    }
}

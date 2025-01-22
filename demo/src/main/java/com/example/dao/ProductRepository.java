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
            sql = "INSERT INTO products (name, price, description, duration_in_days) VALUES (?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO products (name, price, description) VALUES (?, ?, ?)";
        }
    
        // Uso de try-with-resources para gerenciar a conexão e declaração
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            // Configuração dos parâmetros do SQL
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
    
            if (product instanceof ServiceProduct) { // Verifica se o produto é um ServiceProduct
                statement.setInt(4, ((ServiceProduct) product).getDurationInDays());
            }
    
            // Executa a consulta
            statement.executeUpdate();
        }
    }
    

    public void listProducts() throws SQLException {
        String sql = "SELECT * FROM products";
    
        // Uso de try-with-resources para gerenciar a conexão, declaração e conjunto de resultados
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
    
            // Itera pelos resultados e imprime os dados
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int durationInDays = resultSet.getInt("duration_in_days");
    
                // Verifica se o produto é um serviço
                if (durationInDays > 0) {
                    System.out.printf("Service Product: %s, Price: %.2f, Description: %s, Duration: %d days%n",
                            name, price, description, durationInDays);
                } else {
                    System.out.printf("Product: %s, Price: %.2f, Description: %s%n",
                            name, price, description);
                }
            }
        }
    }
}    

package com.example.dao;

import com.example.models.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ProductRepository {
    public void saveProduct(Product product) throws SQLException {
        Connection connection = DataBaseConnection.getConnection();
        String sql = "INSERT INTO products (name, price, description, durationInDays) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setString(3, product.getDescription());
        statement.setInt(4, product.getDurationInDays());
        statement.executeUpdate();
    }

    public void listProducts() throws SQLException {
        String sql = "SELECT * FROM products";
        Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }
    }
}

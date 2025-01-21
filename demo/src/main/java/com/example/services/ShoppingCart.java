package com.example.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.dao.DataBaseConnection;

public class ShoppingCart {

    private int cartId; // ID do carrinho no banco de dados

    public ShoppingCart(int cartId) {
        this.cartId = cartId;
    }

    // Adiciona um produto ao carrinho
    public void addProduct(int productId, int quantity) throws SQLException {
        String sql = "INSERT INTO cart_items (cart_id, product_id, quantity) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE quantity = quantity + ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            statement.setInt(4, quantity);
            statement.executeUpdate();
        }
    }

    // Remove um produto do carrinho
    public void removeProduct(int productId) throws SQLException {
        String sql = "DELETE FROM cart_items WHERE cart_id = ? AND product_id = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            statement.setInt(2, productId);
            statement.executeUpdate();
        }
    }

    // Lista os produtos no carrinho
    public void listCartContents() throws SQLException {
        String sql = """
                     SELECT p.name, p.price, p.description, ci.quantity 
                     FROM products p
                     INNER JOIN cart_items ci ON p.id = ci.product_id
                     WHERE ci.cart_id = ?
                     """;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Conteúdo do Carrinho:");
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    String description = resultSet.getString("description");
                    int quantity = resultSet.getInt("quantity");
                    System.out.printf("Produto: %s, Preço: %.2f, Descrição: %s, Quantidade: %d%n",
                            name, price, description, quantity);
                }
            }
        }
    }

    // Calcula o valor total do carrinho
    public double calculateTotal() throws SQLException {
        String sql = """
                     SELECT SUM(p.price * ci.quantity) AS total 
                     FROM products p
                     INNER JOIN cart_items ci ON p.id = ci.product_id
                     WHERE ci.cart_id = ?
                     """;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("total");
                }
            }
        }
        return 0.0;
    }

    // Limpa o carrinho
    public void clearCart() throws SQLException {
        String sql = "DELETE FROM cart_items WHERE cart_id = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            statement.executeUpdate();
        }
    }
}

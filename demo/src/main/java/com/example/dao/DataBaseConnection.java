package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// SRP - responsabilidade unica. Cria e fornece uma conexao com o banco
public class DataBaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_simulador_loja_virtual";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


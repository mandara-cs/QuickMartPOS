package com.quickmart.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = System.getenv().getOrDefault(
                        "QUICKMART_DB_URL",
                        "jdbc:mysql://localhost:3306/quickmart_db?useSSL=false&allowPublicKeyRetrieval=true");
                String user = System.getenv().getOrDefault("QUICKMART_DB_USER", "root");
                String password = System.getenv("QUICKMART_DB_PASSWORD");
                if (password == null || password.isBlank()) {
                    throw new SQLException("QUICKMART_DB_PASSWORD environment variable is not set.");
                }
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                System.err.println("Driver Class Not Found!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}

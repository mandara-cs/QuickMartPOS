package com.quickmart.db;

import com.quickmart.util.AppLogger;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection = null;

    public static Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = System.getenv().getOrDefault(
                    "QUICKMART_DB_URL",
                    "jdbc:mysql://localhost:3306/quickmart_db?useSSL=false&allowPublicKeyRetrieval=true"
                );
                String user = System.getenv().getOrDefault("QUICKMART_DB_USER", "root");
                String password = System.getenv("QUICKMART_DB_PASSWORD");
                if (password == null || password.isBlank()) {
                    throw new IllegalStateException("QUICKMART_DB_PASSWORD environment variable is not set.");
                }
                connection = DriverManager.getConnection(
                    url, user, password
                );
                AppLogger.info("Database connection established successfully.");
            } catch (Exception e) {
                AppLogger.error("Database Connection Failed", e);
                throw e;
            }
        }
        return connection;
    }
}

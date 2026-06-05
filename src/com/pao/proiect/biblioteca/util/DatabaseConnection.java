package com.pao.proiect.biblioteca.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            // 1. Citim db.properties din resources/
            Properties props = new Properties();

            try (InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("db.properties")) {

                if (is == null) {
                    throw new RuntimeException("Nu gasesc db.properties in resources/");
                }

                props.load(is);
            }

            // 2. Extragem datele de conectare
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // 3. Conectare JDBC (MySQL)
            this.connection = DriverManager.getConnection(url, user, password);

        } catch (IOException | SQLException e) {
            throw new RuntimeException("Eroare la conectarea la baza de date", e);
        }
    }

    // Singleton
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Expune conexiunea
    public Connection getConnection() {
        return connection;
    }

    // Închidere conexiune (folosit la final de aplicație)
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Eroare la inchiderea conexiunii", e);
            }
        }
    }
}
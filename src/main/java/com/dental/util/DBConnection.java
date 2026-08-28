package com.dental.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection - implemented using the SINGLETON DESIGN PATTERN.
 *
 * Why Singleton?
 * - Only ONE instance of the database connection manager should exist
 *   throughout the application's lifetime.
 * - Prevents multiple redundant connection objects being created every
 *   time a DAO class needs to talk to the database.
 * - Provides a single, global access point (getInstance()) to obtain
 *   the connection, which is a textbook use case for the Singleton pattern.
 *
 * How it works:
 * - The constructor is private, so no other class can do "new DBConnection()".
 * - A single static instance is created lazily (only when first needed).
 * - getConnection() checks if the existing connection is closed/null and
 *   only opens a new one when necessary, otherwise reuses the same one.
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/sunrise_dentals";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // The single, shared instance of this class (Singleton instance)
    private static DBConnection instance;

    // The single, shared JDBC connection object
    private Connection connection;

    // Private constructor -> prevents "new DBConnection()" from outside this class
    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Global access point for the Singleton instance.
     * Creates the instance only once (lazy initialization).
     */
    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    /**
     * Returns a live connection. Reconnects automatically if the
     * existing connection was closed for some reason.
     *
     * Kept as a STATIC method so existing DAO code
     * (DBConnection.getConnection()) keeps working with ZERO changes.
     */
    public static Connection getConnection() {
        try {
            DBConnection singleton = getInstance();

            if (singleton.connection == null || singleton.connection.isClosed()) {
                singleton.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }

            return singleton.connection;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

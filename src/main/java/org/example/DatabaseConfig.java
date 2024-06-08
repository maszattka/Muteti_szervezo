package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String URL = "jdbc:mysql://localhost:3306/surgery_planner";
    private static final String USER = "root"; // vagy az adatbázis felhasználóneved
    private static final String PASSWORD = "RenataRobin2008"; // vagy az adatbázis jelszavad

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
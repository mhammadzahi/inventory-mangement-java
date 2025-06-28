package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

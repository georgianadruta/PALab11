package com.example.demo.database;

import java.sql.*;

/**
 * helpful class to manage access to db
 */
public class Database {
    private static Connection con = null;

    static {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "student";
        String pass = "STUDENT";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
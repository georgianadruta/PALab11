package com.example.demo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    public ResultSet rs;
    private Connection con;

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() { //establishes a connection
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "STUDENT");
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet setResultSet(String query) { //will execute any query
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void doUpdate(String update) { //will execute updates
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(update);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
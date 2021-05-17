package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * an helpful class for database
 */
public class Database {
    public ResultSet rs;
    private Connection con;

    private static final Logger logger= Logger.getLogger(String.valueOf(Database.class));
    private static final Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    /**
     * establishes a connection
     */
    private Database() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student", "student");
            logger.info("Connected to database");
        } catch (Exception exception) {
            logger.info("Exception in database constructor.");
        }
    }

    /**
     * will execute any query
     */
    public ResultSet setResultSet(String query) {
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception exception) {
            logger.info("Exception in setResultSet method.");
        }
        return rs;
    }

    /**
     * will execute updates
     */
    public void doUpdate(String update) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(update);
        } catch (Exception exception) {
           logger.info("Exception in doUpdate method.");
        }
    }
}

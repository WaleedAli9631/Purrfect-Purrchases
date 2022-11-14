package com.revature.project2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
    public static Connection getConnection() throws SQLException {
        //Revature says don't hardcode the following..
        //But our trainers will need it to work it.
        String url = "jdbc:postgresql://104.154.144.63:5432/postgres";
        String username = "postgres";
        String password = "T/Q+?XV06$)08^Rz";

        return DriverManager.getConnection(url,username,password);
    }
}

package org.example;

import java.sql.*;

public class DBUtil {


    private static final String URL = "jdbc:postgresql://localhost:5432/library_prod";
    private static final String USER = "postgres";
    private static final String PASS = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

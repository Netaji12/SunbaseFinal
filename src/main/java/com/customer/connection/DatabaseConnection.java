package com.customer.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sunbase";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    private static volatile Connection connection;

    
    public static Connection getConnection() {
        if (connection == null) {
        	try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                // Log the exception or handle it more appropriately
                e.printStackTrace();
            }
        }
        System.out.println("connection establesed....");
        return connection;
    }

}

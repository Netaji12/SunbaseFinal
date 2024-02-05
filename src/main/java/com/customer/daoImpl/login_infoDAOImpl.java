package com.customer.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.customer.dao.login_infoDAO;

public class login_infoDAOImpl implements login_infoDAO {

    // JDBC connection parameters (replace with your database configuration)
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sunbase";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(String login_id, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establishing a database connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Preparing SQL query
            String sql = "SELECT * FROM login_info WHERE login_id = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login_id);
            preparedStatement.setString(2, password);

            // Executing the query
            resultSet = preparedStatement.executeQuery();

            // Checking if the user exists (authentication successful)
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
            return false;
        } finally {
            // Closing resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	public String getCustomerName(String login_id) {
		// TODO Auto-generated method stub
		return null;
	}

    // Other methods related to login_info can be implemented as needed
}

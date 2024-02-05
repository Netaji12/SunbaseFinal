package com.customer.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.customer.connection.DatabaseConnection;
import com.customer.dao.customer_infoDAO;
import com.customer.model.customerInfo_model;

public class customer_info_Impl implements customer_infoDAO {

  

    // SQL statements
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customer (first_name, last_name, street, address, city, state, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE customer SET first_name=?, last_name=?, street=?, address=?, city=?, state=?, email=?, phone=? WHERE customer_id=?";
    private static final String SELECT_ALL_CUSTOMERS_SQL = "SELECT * FROM customer";
    private static final String SELECT_CUSTOMER_BY_ID_SQL = "SELECT * FROM customer WHERE customer_id=?";
    private static final String DELETE_CUSTOMER_BY_ID_SQL = "DELETE FROM customer WHERE customer_id=?";
    private static final String SELECT_CUSTOMERS_BY_FILTER_SQL = "SELECT * FROM customer WHERE %s LIKE ?";
    private static final String SELECT_CUSTOMERS_PAGINATED_SQL = "SELECT * FROM customer LIMIT ?, ?";
    private static final String SELECT_TOTAL_CUSTOMERS_COUNT_SQL = "SELECT COUNT(*) FROM customer";



    @Override
    public boolean addCustomer(customerInfo_model customer) {
    	Connection connection = DatabaseConnection.getConnection();
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL);
            preparedStatement.setString(1, customer.getFirst_name());
            preparedStatement.setString(2, customer.getLast_name());
            preparedStatement.setString(3, customer.getStreet());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getCity());
            preparedStatement.setString(6, customer.getState());
            preparedStatement.setString(7, customer.getEmail());
            preparedStatement.setString(8, customer.getPhone());

            int rowsAffected = preparedStatement.executeUpdate();
            // If one or more rows were affected, consider it a success
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
        return false;
    }

    public boolean updateCustomer(customerInfo_model customer) {
    	Connection connection = DatabaseConnection.getConnection();
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL);
            preparedStatement.setString(1, customer.getFirst_name());
            preparedStatement.setString(2, customer.getLast_name());
            preparedStatement.setString(3, customer.getStreet());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getCity());
            preparedStatement.setString(6, customer.getState());
            preparedStatement.setString(7, customer.getEmail());
            preparedStatement.setString(8, customer.getPhone());
            preparedStatement.setInt(9, customer.getCustomer_id());

            int rowsAffected = preparedStatement.executeUpdate();

            // If one or more rows were affected, consider it a success
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
        return false;
    }

    @Override
    public List<customerInfo_model> getListOfCustomers() {
        List<customerInfo_model> customers = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerInfo_model customer = new customerInfo_model();
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setFirst_name(resultSet.getString("first_name"));
                customer.setLast_name(resultSet.getString("last_name"));
                customer.setStreet(resultSet.getString("street"));
                customer.setAddress(resultSet.getString("address"));
                customer.setCity(resultSet.getString("city"));
                customer.setState(resultSet.getString("state"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPhone(resultSet.getString("phone"));

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
        return customers;
    }

    @Override
    public customerInfo_model getCustomerById(int customerId) {
    	Connection connection = DatabaseConnection.getConnection();
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID_SQL);

            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                customerInfo_model customer = new customerInfo_model();
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setFirst_name(resultSet.getString("first_name"));
                customer.setLast_name(resultSet.getString("last_name"));
                customer.setStreet(resultSet.getString("street"));
                customer.setAddress(resultSet.getString("address"));
                customer.setCity(resultSet.getString("city"));
                customer.setState(resultSet.getString("state"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPhone(resultSet.getString("phone"));

                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
        return null;
    }

    @Override
    public boolean deleteCustomerById(int customerId) {
    	Connection connection = DatabaseConnection.getConnection();
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_BY_ID_SQL);

            preparedStatement.setInt(1, customerId);
            int rowsAffected = preparedStatement.executeUpdate();
            // If one or more rows were affected, consider it a success
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
        return false;
    }

    // Modify the getFilteredCustomers method to include pagination parameters
    public List<customerInfo_model> getFilteredCustomers(String filterBy, String filterValue, int offset, int limit) {
        List<customerInfo_model> filteredCustomers = new ArrayList<>();
        if (filterValue.isEmpty()) {
            return filteredCustomers;
        }

        String sqlQuery = String.format(SELECT_CUSTOMERS_BY_FILTER_SQL + " LIMIT ?, ?", filterBy);
        Connection connection = DatabaseConnection.getConnection();
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, "%" + filterValue + "%");
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    customerInfo_model customer = new customerInfo_model();
                    customer.setCustomer_id(resultSet.getInt("customer_id"));
                    customer.setFirst_name(resultSet.getString("first_name"));
                    customer.setLast_name(resultSet.getString("last_name"));
                    customer.setStreet(resultSet.getString("street"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setCity(resultSet.getString("city"));
                    customer.setState(resultSet.getString("state"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setPhone(resultSet.getString("phone"));

                    filteredCustomers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
        return filteredCustomers;
    }

    // New method to retrieve a paginated list of customers
    public List<customerInfo_model> getListOfCustomersPaginated(int offset, int limit) {
        List<customerInfo_model> customers = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMERS_PAGINATED_SQL);

            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    customerInfo_model customer = new customerInfo_model();
                    customer.setCustomer_id(resultSet.getInt("customer_id"));
                    customer.setFirst_name(resultSet.getString("first_name"));
                    customer.setLast_name(resultSet.getString("last_name"));
                    customer.setStreet(resultSet.getString("street"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setCity(resultSet.getString("city"));
                    customer.setState(resultSet.getString("state"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setPhone(resultSet.getString("phone"));

                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
        return customers;
    }

    // New method to get the total number of customers
    public int getNumberOfCustomers() {
        int count = 0;
        Connection connection = DatabaseConnection.getConnection();
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOTAL_CUSTOMERS_COUNT_SQL);
             ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }

        return count;
    }

    @Override
    public void updateCustomer(customer_infoDAO customer) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean addCustomer(customer_infoDAO customer) {
        // TODO Auto-generated method stub
        return false;
    }

    // ... (remaining methods remain unchanged)
}
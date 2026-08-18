package com.ashin.vehiclerental.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ashin.vehiclerental.exception.DataAccessException;
import com.ashin.vehiclerental.model.Customer;
import com.ashin.vehiclerental.util.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    String selectQuery = "SELECT * FROM customers";

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try {

            DatabaseConnection databaseConnection = new DatabaseConnection();

            try (Connection connection = databaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");

                    String name = resultSet.getString("name");

                    String email = resultSet.getString("email");

                    String phone = resultSet.getString("phone");

                    Customer customer = new Customer(id, name, email, phone);

                    customers.add(customer);
                }

                
            } 
        } 
        catch (SQLException | IOException e)  
        {
           throw new DataAccessException("Failed to load Customer",e);
        }
        return customers;

    }

    public void saveCustomer(Customer customer) {
        String insertQuery = "INSERT INTO customers(name,phone,email) values(?,?,?)";

        try {

            DatabaseConnection databaseConnection = new DatabaseConnection();

            try (Connection connection = databaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery))

            {

                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getPhone());
                preparedStatement.setString(3, customer.getEmail());

                preparedStatement.executeUpdate();

            }

        }
        catch (SQLException | IOException e) {
          throw new DataAccessException("Failed to Save The Customer",e);
        }

    }

    public boolean existByEmail(String email) {

        String emailExistQuery = " SELECT EXISTS (\r\n" + //
                "    SELECT 1\r\n" + //
                "    FROM customers\r\n" + //
                "    WHERE email = ?\r\n" + //
                ") AS is_present;\r\n" + //
                "";

        try {

            DatabaseConnection databaseConnection = new DatabaseConnection();

            try (Connection connection = databaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(emailExistQuery)
                    )

                    
                {
                    preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) 
                {
                    
                    if (resultSet.next()) {
                        int status = resultSet.getInt("is_present");
    
                        return status == 1;
                    }
    
                    throw new DataAccessException(" No result returned while checking customer email ");
                } 


            }
        } catch (SQLException | IOException e) {
            throw new DataAccessException("Failed to check Customer Email", e);
        }

    }

    public boolean existByPhone(String phone) {
        String phoneExistQuery = " SELECT EXISTS (\r\n" + //
                "    SELECT 1\r\n" + //
                "    FROM customers\r\n" + //
                "    WHERE phone = ?\r\n" + //
                ") AS is_present;\r\n" + //
                "";

                try {
                    
                    DatabaseConnection databaseConnection = new DatabaseConnection();
            
                    try (Connection connection = databaseConnection.getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement(phoneExistQuery)
                           ) 
                            
                        {
                                preparedStatement.setString(1, phone);
                            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                                
                                if (resultSet.next()) {
                                    int status = resultSet.getInt("is_present");
                    
                                    return status == 1;
                                }
                    
                                throw new SQLException(" No result returned while checking customer Phone Number ");
                            } 

                } 
                
            }
            catch (SQLException | IOException e) 
            {
                throw new DataAccessException(" Failed to Check Phone Number ",e);
            }

    }

    public boolean existsById(int customerId)
    {
        String idExistQuery = """
                SELECT EXISTS (
                    SELECT 1
                    FROM customers
                    WHERE id = ?
                ) AS is_present
                """;

            try {
                    DatabaseConnection databaseConnection = new DatabaseConnection();

                try (Connection connection = databaseConnection.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(idExistQuery)) 
                {
                    preparedStatement.setInt(1, customerId);
                    
                    try (ResultSet resultSet = preparedStatement.executeQuery()) 
                    {

                            if(resultSet.next())
                            {

                                int status = resultSet.getInt("is_present");
        
                               return status==1;
                            }
                    } 

                    throw new DataAccessException(" No result returned while checking customer Id ");
                } 
                
            } catch (SQLException | IOException e) 
            {
                    throw new DataAccessException(" Failed to Check Id ",e);    
            }


    }

}

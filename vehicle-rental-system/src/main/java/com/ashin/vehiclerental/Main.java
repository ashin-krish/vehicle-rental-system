package com.ashin.vehiclerental;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ashin.vehiclerental.model.Customer;
import com.ashin.vehiclerental.util.DatabaseConnection;

public class Main 
{

       public static void main(String[] args) 
       {

            // String checkDbQuery = "Show Databases";

            String checkCustomerQuery = "SELECT * FROM customers";

            // String insertQuery = "INSERT INTO customers(name,phone,email) VALUES(?,?,?)";
            try
            {
                DatabaseConnection databaseConnection = new DatabaseConnection();


                try( Connection connection = databaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(checkCustomerQuery);
                    ResultSet resultSet = preparedStatement.executeQuery();
                       )
                {
                //    preparedStatement.setString(1, "Ashin");
                //    preparedStatement.setString(2, "12345678");
                //    preparedStatement.setString(3, "ash@gmail.com");

                //    int row = preparedStatement.executeUpdate();

                //    System.out.println(row + " Affected ");


                
                while (resultSet.next()) 
                    {
                            int id = resultSet.getInt("id");
            
                            String name = resultSet.getString("name");
            
                            String email = resultSet.getString("email");
            
                            String phone = resultSet.getString("phone");
            
                            Customer customer = new Customer(id,name, email, phone);
                            
                            System.out.println(customer.getId());
                            System.out.println(customer.getName());
                            System.out.println(customer.getEmail());
                            System.out.println(customer.getPhone());
                        }



                }
                                    
                
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
       }
}

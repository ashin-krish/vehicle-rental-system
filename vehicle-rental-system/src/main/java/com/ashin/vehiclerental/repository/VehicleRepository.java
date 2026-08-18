package com.ashin.vehiclerental.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ashin.vehiclerental.exception.DataAccessException;
import com.ashin.vehiclerental.model.Vehicle;
import com.ashin.vehiclerental.util.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository 
{
    public void saveVehicle(Vehicle vehicle)
    {

            String insertQuery = "INSERT INTO vehicles(registration_number, brand, model, type, price_per_day, vehicle_status) VALUES(?,?,?,?,?,?)";
            try {
                
                DatabaseConnection databaseConnection = new DatabaseConnection();

                try(Connection connection = databaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) 
                {
                   preparedStatement.setString(1, vehicle.getRegistrationNumber());
                   preparedStatement.setString(2, vehicle.getBrand());
                   preparedStatement.setString(3, vehicle.getModel());
                   preparedStatement.setString(4, vehicle.getType());
                   preparedStatement.setInt(5, vehicle.getPricePerDay());
                   preparedStatement.setString(6, vehicle.getVehicleStatus().name());

                   preparedStatement.executeUpdate();
                } 
            } catch (SQLException | IOException e) 
            {
                throw new DataAccessException("Failed to Save vehicle", e);
            }

    }

    public List<Vehicle> getAllVehicles()
    {
        List<Vehicle> vehicles = new ArrayList<>();

        String selectQuery = "SELECT * FROM vehicles";
        

        try 
        {
                DatabaseConnection databaseConnection = new DatabaseConnection();

                try (Connection connection =databaseConnection.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                        ResultSet resultSet = preparedStatement.executeQuery()) 
                {
                    while (resultSet.next()) 
                    {
                           int id = resultSet.getInt("id");
                            String registrationNumber = resultSet.getString("registration_number");
                            String brand = resultSet.getString("brand");
                            String model = resultSet.getString("model");
                            String type = resultSet.getString("type");
                            int pricePerDay = resultSet.getInt("price_per_day");
                            String status = resultSet.getString("vehicle_status");

                            Vehicle.VehicleStatus vehicleStatus = Vehicle.VehicleStatus.valueOf(status);

                            Vehicle vehicle = new Vehicle(id,registrationNumber, brand, model, type, pricePerDay, vehicleStatus);

                            vehicles.add(vehicle);
                    }  
                }
    
       
        } 
        catch (SQLException | IOException e) 
        {
            throw new DataAccessException("Failed to Load Vehicle",e);
        }

        return vehicles;


    }



    public boolean existsByRegistrationNumber(String registrationNumber)
    {
        String existsByRegistrationNumberQuery = "SELECT EXISTS (\r\n" + //
                        "    SELECT 1\r\n" + //
                        "    FROM vehicles\r\n" + //
                        "    WHERE registration_number = ?\r\n" + //
                        ") AS is_present";

        try {
            
                DatabaseConnection databaseConnection = new DatabaseConnection();

                try (Connection connection = databaseConnection.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(existsByRegistrationNumberQuery)) 
                {

                        preparedStatement.setString(1, registrationNumber);
                        try (ResultSet resultSet = preparedStatement.executeQuery()) 
                        {
                                if(resultSet.next())
                                {
                                    int status = resultSet.getInt("is_present");

                                    return status == 1;
                                }
                                throw new DataAccessException(" No result returned while checking Vehicle Registration Number");
                            } 

                } 

        } catch (SQLException | IOException e) 
    {
            throw new DataAccessException(" Failed to check Registration Number ", e);
    }
         
}


       public boolean isVehicleAvailable(int vehicleId)
       {
            String vehicleAvailabilitySearchQuery="""
                                            SELECT EXISTS (
                            SELECT 1
                            FROM vehicles
                            WHERE id = ?
                            AND vehicle_status = 'AVAILABLE'
                        ) AS is_present;
                    """;

            try 
            {
                DatabaseConnection databaseConnection = new DatabaseConnection();

                try (Connection connection = databaseConnection.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(vehicleAvailabilitySearchQuery)) 
                {
                        preparedStatement.setInt(1, vehicleId);

                        try (ResultSet resultSet = preparedStatement.executeQuery()) 
                        {
                            if(resultSet.next())
                            {
                                int status = resultSet.getInt("is_present");
                                return status == 1;
                            }

                            throw new DataAccessException("No result returned while checking Vehicle Registration Number");
                        } 

                } 
            } catch (SQLException | IOException e) 
            {
                throw new DataAccessException(" Failed to Check The Availability of the Vechile " , e);
            }
       }

       public int updateVehicleStatus(int vehicleId,Vehicle.VehicleStatus vehicleStatus)
       {
            String updateVehicleStatusQuery = " UPDATE vehicles set vehicle_status = ? WHERE  id = ? ";

            try 
            {
                    DatabaseConnection databaseConnection = new DatabaseConnection();
                    
                    try (Connection connection = databaseConnection.getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement(updateVehicleStatusQuery)) 
                    {
                            
                        String enumToStringstatus = vehicleStatus.name();

                        preparedStatement.setString(1, enumToStringstatus);

                        preparedStatement.setInt(2, vehicleId);

                       int rowsAffected = preparedStatement.executeUpdate();

                       return rowsAffected;
                    } 


            } catch (SQLException | IOException e) 
            {
                throw new DataAccessException(" Failed to Update The Status " ,e);
            }
       }

       public boolean existById(int id)
       {
            String vehicleIdExistQuery = """
                    SELECT EXISTS (
                    SELECT 1
                    FROM vehicles
                    WHERE id = ?
                ) AS is_present;
                    """;

                try {
                    DatabaseConnection databaseConnection = new DatabaseConnection();

                    try (Connection connection = databaseConnection.getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement(vehicleIdExistQuery)) 
                    {
                            preparedStatement.setInt(1, id);
                            
                        try (ResultSet resultSet = preparedStatement.executeQuery()) 
                        {
                            
                            if(resultSet.next())
                                {
                                    int status = resultSet.getInt("is_present");

                                    return status == 1; 
                                }

                        
                                throw new DataAccessException(" No result returned while checking Vehicle Id  ");

                        } 
                    } 
                } catch (SQLException | IOException e) 
                {
                    throw new DataAccessException(" Failed to check The vehicle Id ",e);    
                }
       }
       

}

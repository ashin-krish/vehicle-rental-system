package com.ashin.vehiclerental.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ashin.vehiclerental.exception.DataAccessException;
import com.ashin.vehiclerental.model.Vehicle;
import com.ashin.vehiclerental.util.DatabaseConnection;

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
        
}

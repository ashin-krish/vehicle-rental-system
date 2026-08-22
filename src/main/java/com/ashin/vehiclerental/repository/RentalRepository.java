package com.ashin.vehiclerental.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ashin.vehiclerental.exception.DataAccessException;
import com.ashin.vehiclerental.model.Rental;
import com.ashin.vehiclerental.util.DatabaseConnection;

public class RentalRepository {
    public int saveRental(Rental rental) {
        String rentalInsertQuery = """
                INSERT INTO rentals(customer_id, vehicle_id, rental_date, return_date)
                VALUES(?,?,?,?)
                """;

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();

            try (Connection connection = databaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(rentalInsertQuery)) {

                preparedStatement.setInt(1, rental.getCustomerId());
                preparedStatement.setInt(2, rental.getVehicleId());
                preparedStatement.setDate(3, java.sql.Date.valueOf(rental.getRentalDate()));

                if (rental.getReturnDate() != null) {
                    preparedStatement.setDate(4, java.sql.Date.valueOf(rental.getReturnDate()));
                } else {
                    preparedStatement.setNull(4, java.sql.Types.DATE);
                }

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected;
            }

        } catch (SQLException | IOException e) {
            throw new DataAccessException("Failed to Save The Rental", e);
        }
    }

    public boolean existsActiveRental(int customerId, int vehicleId) {
        String activeRentalCheckQuery = """
                        SELECT EXISTS (
                    SELECT 1
                    FROM rentals
                    WHERE customer_id = ?
                    AND vehicle_id = ?
                    AND return_date IS NULL
                ) AS is_present;
                        """;

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();

            try (Connection connection = databaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(activeRentalCheckQuery)) {
                preparedStatement.setInt(1, customerId);
                preparedStatement.setInt(2, vehicleId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int status = resultSet.getInt(" is_present ");

                        return status == 1;
                    }
                }
                throw new DataAccessException(" No result returned while Active Rental ");
            }
        } catch (SQLException | IOException e) {
            throw new DataAccessException(" Failed To Check Rental status", e);
        }

    }

    public int returnRental(int customerId, int vehicleId, Connection connection) {
        String vehicleReturnQuery = """
                    UPDATE rentals
                SET return_date = ?
                WHERE customer_id = ?
                AND vehicle_id = ?
                AND return_date IS NULL
                    """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(vehicleReturnQuery)) {

            LocalDate localDate = LocalDate.now();

            preparedStatement.setDate(1, java.sql.Date.valueOf(localDate));
            preparedStatement.setInt(2, customerId);
            preparedStatement.setInt(3, vehicleId);

            return preparedStatement.executeUpdate();

        }

        catch (SQLException e) {
            throw new DataAccessException(" Failed to Update the rental status ", e);
        }
    }

    public int saveRental(Rental rental, Connection connection) {
        String rentalInsertQuery = """
                INSERT INTO rentals(customer_id, vehicle_id, rental_date, return_date)
                VALUES(?,?,?,?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(rentalInsertQuery)) {
            preparedStatement.setInt(1, rental.getCustomerId());
            preparedStatement.setInt(2, rental.getVehicleId());

            preparedStatement.setDate(
                    3,
                    java.sql.Date.valueOf(rental.getRentalDate()));

            if (rental.getReturnDate() != null) {
                preparedStatement.setDate(
                        4,
                        java.sql.Date.valueOf(rental.getReturnDate()));
            } else {
                preparedStatement.setNull(4, java.sql.Types.DATE);
            }

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(
                    "Failed to Save The Rental", e);
        }
    }


    public List<Rental> getAllRentals()
    {

        List<Rental> rentals = new ArrayList<>();

        String getRentalQuery = " SELECT * FROM rentals ";

        try 
        {
            DatabaseConnection databaseConnection = new DatabaseConnection();

            try (Connection connection = databaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(getRentalQuery);
                        ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                    while (resultSet.next()) 
                    {
                            int id = resultSet.getInt("id");
                            int CustomerId = resultSet.getInt("customer_id");
                            int VehicleId = resultSet.getInt("vehicle_id");
                            Date rentalDate = resultSet.getDate("rental_date");
                            Date returnDate = resultSet.getDate("return_date");

                           LocalDate rentalLocalDate = rentalDate.toLocalDate();
                           
                           LocalDate returnLocalDate = null;

                            if(returnDate != null)
                            {
                                 returnLocalDate = returnDate.toLocalDate();
                            }

                            Rental rental = new Rental(id, CustomerId, VehicleId, rentalLocalDate, returnLocalDate);

                            rentals.add(rental);

                             
                    }

                
            } 
            return rentals;
         
         } 
        catch (SQLException | IOException e) 
        {
           throw new DataAccessException(" Failed Load The Existing Rental",e);
        }
    }

}

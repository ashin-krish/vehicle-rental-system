package com.ashin.vehiclerental.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.ashin.vehiclerental.exception.DataAccessException;
import com.ashin.vehiclerental.model.Rental;
import com.ashin.vehiclerental.model.Vehicle;
import com.ashin.vehiclerental.repository.CustomerRepository;
import com.ashin.vehiclerental.repository.RentalRepository;
import com.ashin.vehiclerental.repository.VehicleRepository;
import com.ashin.vehiclerental.util.DatabaseConnection;

public class RentalService 
{
        VehicleRepository vehicleRepository = new VehicleRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        
    VehicleService vehicleService = new VehicleService(vehicleRepository);
    CustomerService customerService = new CustomerService(customerRepository);

    RentalRepository rentalRepository = new RentalRepository();

    public void rentVehicle(int customerId, int vehicleId) {
        if (!customerService.existById(customerId)) {
            throw new IllegalArgumentException(" Customer Not Exist ");
        }

        if (!vehicleService.existById(vehicleId)) {
            throw new IllegalArgumentException(" Vehicle Not Exist ");
        }

        if (!vehicleService.isVehicleAvailable(vehicleId)) {
            throw new IllegalArgumentException(" Vehicel Not Available ");
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate returnDate = null;

        Connection connection = null;

        Rental rental = new Rental(customerId, vehicleId, currentDate, returnDate);

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.getConnection();

            connection.setAutoCommit(false);

            int rentaRows = rentalRepository.saveRental(rental, connection);

            int vehicleRows = vehicleService.updateVehicleStatus(vehicleId, Vehicle.VehicleStatus.RENTED, connection);

            if (rentaRows != 1 || vehicleRows != 1) {
                connection.rollback();

                throw new DataAccessException(" Rental Transcation Failed ");
            }

            connection.commit();

        } catch (SQLException | IOException e) {
            if (connection != null) {
                try {

                    connection.rollback();

                } catch (SQLException rollBackException) {
                    e.addSuppressed(rollBackException);
                }
            }

            throw new DataAccessException(
                    "Failed to Rent Vehicle", e);
        }

        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void returnRental(int customerId, int vehicleId) 
    {
    
            Connection connection = null;

        try 
        {

            DatabaseConnection databaseConnection = new DatabaseConnection();

            connection = databaseConnection.getConnection();

            connection.setAutoCommit(false);

            int rentalsRows = rentalRepository.returnRental(customerId, vehicleId, connection);

             if(rentalsRows != 1)
             {
                connection.rollback();
                throw new DataAccessException("No Rental Exist");
             }

           int vehicleRows = vehicleService.updateVehicleStatus(vehicleId, Vehicle.VehicleStatus.AVAILABLE, connection);


          
           if( vehicleRows !=  1)
           {
                connection.rollback();

                throw new DataAccessException(" Failed to Update the return Date ");
           }

           connection.commit();
     
        } 

        catch (SQLException | IOException e) 
        {
            if(connection != null)
            {
                try 
                {
                    connection.rollback();
                } catch (SQLException rollBackException) 
                {
                   e.addSuppressed(rollBackException);
                }
                throw new DataAccessException(" Failed to Update the return Date ");
            }
        }

        finally
        {
            try {
                if(connection != null)
                {
                    connection.close();
                }
            } catch (SQLException e) 
            {
                e.printStackTrace();    
            }
        }

    }


    public List<Rental> getAllRentals()
    {
        return rentalRepository.getAllRentals();
    }

    public List<Rental> getActiveRentals()
    {
        List<Rental> allRentals = getAllRentals();

        List<Rental> activeRentals = allRentals.stream()
                                    .filter(rental -> rental.getReturnDate() == null)
                                    .collect(Collectors.toList());
        return activeRentals;
    }

}

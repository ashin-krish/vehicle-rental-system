package com.ashin.vehiclerental.service;

import java.time.LocalDate;

import com.ashin.vehiclerental.model.Rental;
import com.ashin.vehiclerental.model.Vehicle;
import com.ashin.vehiclerental.repository.RentalRepository;

public class RentalService 
{

    VehicleService vehicleService = new VehicleService();
    CustomerService customerService = new CustomerService();

    RentalRepository rentalRepository = new RentalRepository();
    
        public void rentVehicle(int customerId, int vehicleId)
        {
            if(!customerService.existById(customerId))
            {
                throw new IllegalArgumentException(" Customer Not Exist ");
            }

            if(!vehicleService.existById(vehicleId))
            {
                throw new IllegalArgumentException(" Vehicle Not Exist ");
            }

            if(!vehicleService.isVehicleAvailable(vehicleId))
            {
                throw new IllegalArgumentException(" Vehicel Not Available ");
            }

            LocalDate currentDate = LocalDate.now();
            LocalDate returnDate = null;


            Rental rental = new Rental(customerId, vehicleId, currentDate, returnDate);

           int rowsAffected = rentalRepository.saveRental(rental);

           if(rowsAffected > 0)
           {
                vehicleService.updateVehicleStatus(vehicleId, Vehicle.VehicleStatus.RENTED);
           }

        }

      
        public void returnRental(int customerId,int vehicleId)
        {
            int status = rentalRepository.returnRental(customerId,vehicleId);

            if(status == 0)
            {
                throw new IllegalArgumentException(" No Rental Exist ");
            }

            vehicleService.updateVehicleStatus(vehicleId, Vehicle.VehicleStatus.AVAILABLE);
        }
}



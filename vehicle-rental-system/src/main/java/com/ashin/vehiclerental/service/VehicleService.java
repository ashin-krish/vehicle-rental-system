package com.ashin.vehiclerental.service;

import com.ashin.vehiclerental.repository.VehicleRepository;

import com.ashin.vehiclerental.model.Vehicle;

import java.util.List;

public class VehicleService {

    private VehicleRepository vehicleRepository = new VehicleRepository();

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.getAllVehicles();
    }

    public void registerVehicle(Vehicle vehicle) 
    {
        boolean vehicleExists = vehicleRepository.existsByRegistrationNumber(vehicle.getRegistrationNumber());

        if(vehicleExists)
        {
            throw new IllegalArgumentException(" The Vehicle Already Exist ");
        }
        vehicleRepository.saveVehicle(vehicle);
    }

    public boolean isVehicleAvailable(String registrationNumber)
    {
        return vehicleRepository.isVehicleAvailable(registrationNumber);   
    }

    public void updateVehicleStatus(String registrationNumber,Vehicle.VehicleStatus vehicleStatus)
    {
        int rowsAffected = vehicleRepository.updateVehicleStatus(registrationNumber, vehicleStatus);

        if(rowsAffected == 0)
        {
            throw new IllegalArgumentException(" Vehicle does not exist ");
        }
    }

    

}

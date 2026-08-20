package com.ashin.vehiclerental.service;
import com.ashin.vehiclerental.repository.VehicleRepository;
import com.ashin.vehiclerental.model.Vehicle;

import java.sql.Connection;
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

    public boolean isVehicleAvailable(int vehicleId)
    {
        return vehicleRepository.isVehicleAvailable(vehicleId);   
    }

    public int updateVehicleStatus(int vehicleId,Vehicle.VehicleStatus vehicleStatus,Connection connection)
    {
        int rowsAffected = vehicleRepository.updateVehicleStatus(vehicleId, vehicleStatus);

        if(rowsAffected == 0)
        {
            throw new IllegalArgumentException(" Vehicle does not exist ");
        }

        return rowsAffected;
    }


         public void updateVehicleStatus(int vehicleId,Vehicle.VehicleStatus vehicleStatus)
    {
        int rowsAffected = vehicleRepository.updateVehicleStatus(vehicleId, vehicleStatus);

        if(rowsAffected == 0)
        {
            throw new IllegalArgumentException(" Vehicle does not exist ");
        }

    }

    public boolean existById(int vehicleId)
    {
        return vehicleRepository.existById(vehicleId);
    }


      

    

}

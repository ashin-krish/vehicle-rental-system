package com.ashin.vehiclerental.service;
import com.ashin.vehiclerental.repository.VehicleRepository;
import com.ashin.vehiclerental.model.Vehicle;
import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository)
    {
        this.vehicleRepository=vehicleRepository;
    }

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

    public boolean existsByRegistrationNumber(String registerNumber)
    {
        return vehicleRepository.existsByRegistrationNumber(registerNumber);
    }

    public List<Vehicle> getAvailableVehicles()
    {
        List<Vehicle> vehicles = vehicleRepository.getAllVehicles();

       List<Vehicle> availableVehicles = vehicles.stream()
        .filter(vehicle -> vehicle.getVehicleStatus() == Vehicle.VehicleStatus.AVAILABLE)
        .collect(Collectors.toList());

        return availableVehicles;
    }

    public List<Vehicle> getVehicleByBrand(String brand)
    {
        List<Vehicle> vehicles = vehicleRepository.getAllVehicles();

       List<Vehicle> availableVehicles = vehicles.stream()
        .filter(vehicle -> vehicle.getBrand() == brand)
        .collect(Collectors.toList());

        return availableVehicles;
    }
    public List<Vehicle> getVehicleByType(String type)
    {
        List<Vehicle> vehicles = vehicleRepository.getAllVehicles();

       List<Vehicle> availableVehicles = vehicles.stream()
        .filter(vehicle -> vehicle.getType() == type)
        .collect(Collectors.toList());

        return availableVehicles;
    }

    public List<Vehicle> sortByPrice()
    {
        List<Vehicle> vehicles = vehicleRepository.getAllVehicles();

       List<Vehicle> sortedVehicle = vehicles.stream()
                                        .sorted(Comparator.comparing(Vehicle::getPricePerDay))
                                        .collect(Collectors.toList());

        return sortedVehicle;   
    }

}    

    


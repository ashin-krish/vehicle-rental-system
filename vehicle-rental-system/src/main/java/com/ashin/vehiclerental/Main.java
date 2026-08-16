package com.ashin.vehiclerental;

import java.util.ArrayList;
import java.util.List;

import com.ashin.vehiclerental.model.Vehicle;
// import com.ashin.vehiclerental.model.Vehicle.VehicleStatus;
import com.ashin.vehiclerental.repository.VehicleRepository;


public class Main 
{

       public static void main(String[] args) 
       {

            VehicleRepository vehicleRepository = new VehicleRepository();

            // Vehicle vehicle = new Vehicle("KA89JK09", "BMW", "SuperCar", "Sports", 1000000, VehicleStatus.AVAILABLE);

            // vehicleRepository.saveVehicle(vehicle);

            List<Vehicle> vehicles = new ArrayList<>();


            vehicles = vehicleRepository.getAllVehicles();

            for (Vehicle vehicle : vehicles) {
                    System.out.println(vehicle);
            }


    
       }
}

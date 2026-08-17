package com.ashin.vehiclerental;

// import java.util.ArrayList;
// import java.util.List;

import com.ashin.vehiclerental.model.Vehicle;
import com.ashin.vehiclerental.model.Vehicle.VehicleStatus;
// import com.ashin.vehiclerental.model.Vehicle.VehicleStatus;
// import com.ashin.vehiclerental.repository.VehicleRepository;
import com.ashin.vehiclerental.service.VehicleService;


public class Main 
{

       public static void main(String[] args) 
       {

          //   VehicleRepository vehicleRepository = new VehicleRepository();

            

            // List<Vehicle> vehicles = new ArrayList<>();


            // vehicles = vehicleRepository.getAllVehicles();

            // for (Vehicle vehicle : vehicles) {
            //         System.out.println(vehicle);
            // }

          //   Vehicle vehicle = new Vehicle("KL18UIO", "Hero", "F1", "SUV", 2345, VehicleStatus.AVAILABLE);

            
            VehicleService vehicleService = new VehicleService();

          //   vehicleService.registerVehicle(vehicle);

          vehicleService.updateVehicleStatus("KL18UIO", VehicleStatus.RENTED);



       }
}

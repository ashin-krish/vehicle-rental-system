package com.ashin.vehiclerental.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class vehicleTest 
{
    @Test
    void vehicleValid()
    {
        Vehicle vehicle = new Vehicle("KA09871UI", "BMW", "FZ5", "SuperCar", 10000, Vehicle.VehicleStatus.AVAILABLE);

        assertEquals("KA09871UI", vehicle.getRegistrationNumber());
        assertEquals("BMW", vehicle.getBrand());
        assertEquals("FZ5", vehicle.getModel());
        assertEquals("SuperCar", vehicle.getType());
        assertEquals(10000, vehicle.getPricePerDay());
        assertEquals(Vehicle.VehicleStatus.AVAILABLE, vehicle.getVehicleStatus());


    }


    @Test
    void vehiclecheckInvalidRegisterNumber()
    {
             assertThrows(IllegalArgumentException.class, () ->
         new Vehicle("", "BMW", "F2", "SuperCar", 1998, Vehicle.VehicleStatus.AVAILABLE)
        );


    }

    @Test
    void vehiclecheckInvalidBrand()
    {
             assertThrows(IllegalArgumentException.class, () ->
         new Vehicle("KA09871UI", "", "F2", "SuperCar", 1890, Vehicle.VehicleStatus.AVAILABLE)
        );

    }

    @Test
    void vehiclecheckInvalidModel()
    {
    assertThrows(IllegalArgumentException.class, () ->
         new Vehicle("KA09871UI", "BMW", "", "SuperCar", 1000, Vehicle.VehicleStatus.AVAILABLE)
        );
    }

    @Test
    void vehiclecheckInvalidType()
    {
             assertThrows(IllegalArgumentException.class, () ->
         new Vehicle("KA09871UI", "BMW", "F2", "", 90000, Vehicle.VehicleStatus.AVAILABLE)
        );


    }

    
    @Test
    void vehiclecheckInvalidPricePerDay()
    {
          assertThrows(IllegalArgumentException.class, () ->
         new Vehicle("KA09871UI", "BMW", "F2", "SuperCar", -1, Vehicle.VehicleStatus.AVAILABLE)
        );
  
    }


    @Test
    void vehiclecheckInvalidVehicleStatus()
    {
        assertThrows(IllegalArgumentException.class, () ->
         new Vehicle("KA09871UI", "BMW", "F2", "SuperCar", 100000, null)
        );
    }

    
   
  
}

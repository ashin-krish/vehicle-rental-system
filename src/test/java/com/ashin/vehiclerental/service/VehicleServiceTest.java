package com.ashin.vehiclerental.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.ashin.vehiclerental.model.Vehicle;
import com.ashin.vehiclerental.repository.VehicleRepository;

public class VehicleServiceTest 
{
    @Test
    void vehicleRegisterExistByRegisterNumber()
    {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.existsByRegistrationNumber("KA101U")).thenReturn(true);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.existsByRegistrationNumber("KA101U");

        assertTrue(result);

    }


    @Test
    void vehicleRegisterExistByRegisterNumberInvalid()
    {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.existsByRegistrationNumber("KA101U")).thenReturn(false);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.existsByRegistrationNumber("KA101U");

        assertFalse(result);

    }



    @Test
    void vehicleRegisterExistByValid()
    {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.existById(1)).thenReturn(true);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.existById(1);

        assertTrue(result);

     }

     


    @Test
    void vehicleRegisterExistByInvalid()
    {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.existById(1)).thenReturn(false);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.existById(1);

        assertFalse(result);

     }



    @Test
    void vehicleCheckIsAvailable()
    {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.isVehicleAvailable(1)).thenReturn(true);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.isVehicleAvailable(1);

        assertTrue(result);

     }


    @Test
    void vehicleCheckIsAvailableInvalid()
    {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.isVehicleAvailable(1)).thenReturn(false);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.isVehicleAvailable(1);

        assertFalse(result);

     }

     @Test
     void vehicleUpdateCheck()
     {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.updateVehicleStatus(1, Vehicle.VehicleStatus.RENTED)).thenReturn(1);

          VehicleService vehicleService = new VehicleService(mockRepository);

          int result = vehicleService.updateVehicleStatus(1, Vehicle.VehicleStatus.RENTED, null);

          assertEquals(1, result);

     }


     @Test
     void vehicleUpdateCheckInvalid()
     {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.updateVehicleStatus(1, Vehicle.VehicleStatus.RENTED)).thenReturn(0);

          VehicleService vehicleService = new VehicleService(mockRepository);

         assertThrows(IllegalArgumentException.class,() -> vehicleService.updateVehicleStatus(1,Vehicle.VehicleStatus.RENTED,null));

     }


}

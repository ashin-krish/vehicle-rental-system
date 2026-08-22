package com.ashin.vehiclerental.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ashin.vehiclerental.model.Vehicle;
import com.ashin.vehiclerental.repository.VehicleRepository;

public class VehicleServiceTest {
    @Test
    void vehicleRegisterExistByRegisterNumber() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.existsByRegistrationNumber("KA101U")).thenReturn(true);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.existsByRegistrationNumber("KA101U");

        assertTrue(result);

    }

    @Test
    void vehicleRegisterExistByRegisterNumberInvalid() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.existsByRegistrationNumber("KA101U")).thenReturn(false);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.existsByRegistrationNumber("KA101U");

        assertFalse(result);

    }

    @Test
    void vehicleRegisterExistByValid() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.existById(1)).thenReturn(true);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.existById(1);

        assertTrue(result);

    }

    @Test
    void vehicleRegisterExistByInvalid() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.existById(1)).thenReturn(false);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.existById(1);

        assertFalse(result);

    }

    @Test
    void vehicleCheckIsAvailable() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.isVehicleAvailable(1)).thenReturn(true);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.isVehicleAvailable(1);

        assertTrue(result);

    }

    @Test
    void vehicleCheckIsAvailableInvalid() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.isVehicleAvailable(1)).thenReturn(false);

        VehicleService vehicleService = new VehicleService(mockRepository);

        boolean result = vehicleService.isVehicleAvailable(1);

        assertFalse(result);

    }

    @Test
    void vehicleUpdateCheck() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.updateVehicleStatus(1, Vehicle.VehicleStatus.RENTED)).thenReturn(1);

        VehicleService vehicleService = new VehicleService(mockRepository);

        int result = vehicleService.updateVehicleStatus(1, Vehicle.VehicleStatus.RENTED, null);

        assertEquals(1, result);

    }

    @Test
    void vehicleUpdateCheckInvalid() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.updateVehicleStatus(1, Vehicle.VehicleStatus.RENTED)).thenReturn(0);

        VehicleService vehicleService = new VehicleService(mockRepository);

        assertThrows(IllegalArgumentException.class,
                () -> vehicleService.updateVehicleStatus(1, Vehicle.VehicleStatus.RENTED, null));

    }

    @Test
    void availabelVehicleCheckEmpty() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        when(mockRepository.getAllVehicles()).thenReturn(List.of());

        VehicleService vehicleService = new VehicleService(mockRepository);

        List<Vehicle> vehicles = vehicleService.getAvailableVehicles();

        assertTrue(vehicles.isEmpty());

        verify(mockRepository).getAllVehicles();
    }

    @Test
    void availabelVehicleCheck() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        Vehicle availableVehicle = new Vehicle(
                "KA01AB1234",
                "Toyota",
                "Innova",
                "SUV",
                2000,
                Vehicle.VehicleStatus.AVAILABLE);

        Vehicle rentedVehicle = new Vehicle(
                "KA02CD5678",
                "Honda",
                "City",
                "Sedan",
                1500,
                Vehicle.VehicleStatus.RENTED);

        Vehicle maintenanceVehicle = new Vehicle(
                "KA03EF9012",
                "Hyundai",
                "Creta",
                "SUV",
                1800,
                Vehicle.VehicleStatus.MAINTENANCE);

        when(mockRepository.getAllVehicles()).thenReturn(List.of(availableVehicle, rentedVehicle, maintenanceVehicle));

        VehicleService vehicleService = new VehicleService(mockRepository);

        List<Vehicle> vehicles = vehicleService.getAvailableVehicles();

        assertEquals(1, vehicles.size());

        assertEquals(
                Vehicle.VehicleStatus.AVAILABLE,
                vehicles.get(0).getVehicleStatus());

        verify(mockRepository).getAllVehicles();
    }

    @Test
    void availableCheckByBrand() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        Vehicle availableVehicle = new Vehicle(
                "KA01AB1234",
                "Toyota",
                "Innova",
                "SUV",
                2000,
                Vehicle.VehicleStatus.AVAILABLE);

        Vehicle rentedVehicle = new Vehicle(
                "KA02CD5678",
                "Toyota",
                "City",
                "Sedan",
                1500,
                Vehicle.VehicleStatus.RENTED);

        Vehicle maintenanceVehicle = new Vehicle(
                "KA03EF9012",
                "Toyota",
                "Creta",
                "SUV",
                1800,
                Vehicle.VehicleStatus.MAINTENANCE);

        when(mockRepository.getAllVehicles()).thenReturn(List.of(availableVehicle, rentedVehicle, maintenanceVehicle));

        VehicleService vehicleService = new VehicleService(mockRepository);

        List<Vehicle> vehicles = vehicleService.getVehicleByBrand("Toyota");

        assertEquals(3, vehicles.size());

            assertEquals("Toyota", vehicles.get(0).getType());
            assertEquals("Toyota", vehicles.get(1).getType());
            assertEquals("Toyota", vehicles.get(2).getType());

        verify(mockRepository).getAllVehicles();
    }


    @Test
    void availabelecheckByType() {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        Vehicle availableVehicle = new Vehicle(
                "KA01AB1234",
                "Toyota",
                "Innova",
                "SUV",
                2000,
                Vehicle.VehicleStatus.AVAILABLE);

        Vehicle rentedVehicle = new Vehicle(
                "KA02CD5678",
                "Honda",
                "City",
                "SUV",
                1500,
                Vehicle.VehicleStatus.RENTED);

        Vehicle maintenanceVehicle = new Vehicle(
                "KA03EF9012",
                "BMW",
                "Creta",
                "SUV",
                1800,
                Vehicle.VehicleStatus.MAINTENANCE);

        when(mockRepository.getAllVehicles()).thenReturn(List.of(availableVehicle, rentedVehicle, maintenanceVehicle));

        VehicleService vehicleService = new VehicleService(mockRepository);

        List<Vehicle> vehicles = vehicleService.getVehicleByType("SUV");

        assertEquals(3, vehicles.size());

            assertEquals("SUV", vehicles.get(0).getType());
            assertEquals("SUV", vehicles.get(1).getType());
            assertEquals("SUV", vehicles.get(2).getType());

        verify(mockRepository).getAllVehicles();
    }

    @Test
    void sortVehicleByPriceCheck() 
    {
        VehicleRepository mockRepository = mock(VehicleRepository.class);

        Vehicle availableVehicle = new Vehicle(
                "KA01AB1234",
                "Toyota",
                "Innova",
                "SUV",
                2000,
                Vehicle.VehicleStatus.AVAILABLE);

        Vehicle rentedVehicle = new Vehicle(
                "KA02CD5678",
                "Honda",
                "City",
                "SUV",
                1500,
                Vehicle.VehicleStatus.RENTED);

        Vehicle maintenanceVehicle = new Vehicle(
                "KA03EF9012",
                "BMW",
                "Creta",
                "SUV",
                1800,
                Vehicle.VehicleStatus.MAINTENANCE);

        when(mockRepository.getAllVehicles()).thenReturn(List.of(availableVehicle, rentedVehicle, maintenanceVehicle));

        VehicleService vehicleService = new VehicleService(mockRepository);

        List<Vehicle> vehicles = vehicleService.sortByPrice();

        assertEquals(3, vehicles.size());

            assertEquals(1500, vehicles.get(0).getPricePerDay());
            assertEquals(1800, vehicles.get(1).getPricePerDay());
            assertEquals(2000, vehicles.get(2).getPricePerDay());

        verify(mockRepository).getAllVehicles();
    }

}

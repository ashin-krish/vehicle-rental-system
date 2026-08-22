package com.ashin.vehiclerental.ui;

import com.ashin.vehiclerental.model.Vehicle;
import com.ashin.vehiclerental.repository.VehicleRepository;
import com.ashin.vehiclerental.service.VehicleService;
import java.util.List;
import java.util.Scanner;

public class VehicleUI {

    private VehicleService vehicleService;
    private Scanner scanner;

    public VehicleUI(Scanner scanner) {
        this.scanner = scanner;
        VehicleRepository vehicleRepository = new VehicleRepository();
        this.vehicleService = new VehicleService(vehicleRepository);
    }

    public void displayVehicleMenu() {
        boolean running = true;

        while (running) {
            ConsoleUI.printHeader("VEHICLE MANAGEMENT");
            ConsoleUI.printMenu(
                    "Register Vehicle",
                    "View All Vehicles",
                    "View Available Vehicles",
                    "Search by Brand",
                    "Search by Type",
                    "Sort by Price",
                    "Back"
            );

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    registerVehicle();
                    break;
                case "2":
                    viewAllVehicles();
                    break;
                case "3":
                    viewAvailableVehicles();
                    break;
                case "4":
                    searchByBrand();
                    break;
                case "5":
                    searchByType();
                    break;
                case "6":
                    sortByPrice();
                    break;
                case "7":
                    running = false;
                    break;
                default:
                    ConsoleUI.printError("Invalid choice. Please try again.");
            }
            ConsoleUI.printEmptyLine();
        }
    }

    private void registerVehicle() {
        ConsoleUI.printHeader("REGISTER VEHICLE");

        try {
            System.out.print("Enter registration number: ");
            String registrationNumber = scanner.nextLine().trim();

            if (registrationNumber.isEmpty()) {
                ConsoleUI.printError("Registration number cannot be empty.");
                return;
            }

            System.out.print("Enter brand: ");
            String brand = scanner.nextLine().trim();

            if (brand.isEmpty()) {
                ConsoleUI.printError("Brand cannot be empty.");
                return;
            }

            System.out.print("Enter model: ");
            String model = scanner.nextLine().trim();

            if (model.isEmpty()) {
                ConsoleUI.printError("Model cannot be empty.");
                return;
            }

            System.out.print("Enter type: ");
            String type = scanner.nextLine().trim();

            if (type.isEmpty()) {
                ConsoleUI.printError("Type cannot be empty.");
                return;
            }

            System.out.print("Enter price per day: ");
            String priceInput = scanner.nextLine().trim();
            int pricePerDay;

            try {
                pricePerDay = Integer.parseInt(priceInput);
                if (pricePerDay < 0) {
                    ConsoleUI.printError("Price cannot be negative.");
                    return;
                }
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Price must be a valid integer.");
                return;
            }

            Vehicle vehicle = new Vehicle(
                    registrationNumber,
                    brand,
                    model,
                    type,
                    pricePerDay,
                    Vehicle.VehicleStatus.AVAILABLE
            );

            vehicleService.registerVehicle(vehicle);
            ConsoleUI.printSuccess("Vehicle registered successfully!");

        } catch (IllegalArgumentException e) {
            ConsoleUI.printError(e.getMessage());
        } catch (Exception e) {
            ConsoleUI.printError("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void viewAllVehicles() {
        ConsoleUI.printHeader("ALL VEHICLES");

        try {
            List<Vehicle> vehicles = vehicleService.getAllVehicles();

            if (vehicles == null || vehicles.isEmpty()) {
                ConsoleUI.printInfo("No vehicles found.");
                return;
            }

            displayVehicleList(vehicles);

        } catch (Exception e) {
            ConsoleUI.printError("Failed to retrieve vehicles: " + e.getMessage());
        }
    }

    private void viewAvailableVehicles() {
        ConsoleUI.printHeader("AVAILABLE VEHICLES");

        try {
            List<Vehicle> vehicles = vehicleService.getAvailableVehicles();

            if (vehicles == null || vehicles.isEmpty()) {
                ConsoleUI.printInfo("No available vehicles found.");
                return;
            }

            displayVehicleList(vehicles);

        } catch (Exception e) {
            ConsoleUI.printError("Failed to retrieve available vehicles: " + e.getMessage());
        }
    }

    private void searchByBrand() {
        ConsoleUI.printHeader("SEARCH BY BRAND");

        try {
            System.out.print("Enter brand to search: ");
            String brand = scanner.nextLine().trim();

            if (brand.isEmpty()) {
                ConsoleUI.printError("Brand cannot be empty.");
                return;
            }

            List<Vehicle> vehicles = vehicleService.getVehicleByBrand(brand);

            if (vehicles == null || vehicles.isEmpty()) {
                ConsoleUI.printInfo("No vehicles found for brand: " + brand);
                return;
            }

            displayVehicleList(vehicles);

        } catch (Exception e) {
            ConsoleUI.printError("Failed to search vehicles: " + e.getMessage());
        }
    }

    private void searchByType() {
        ConsoleUI.printHeader("SEARCH BY TYPE");

        try {
            System.out.print("Enter type to search: ");
            String type = scanner.nextLine().trim();

            if (type.isEmpty()) {
                ConsoleUI.printError("Type cannot be empty.");
                return;
            }

            List<Vehicle> vehicles = vehicleService.getVehicleByType(type);

            if (vehicles == null || vehicles.isEmpty()) {
                ConsoleUI.printInfo("No vehicles found for type: " + type);
                return;
            }

            displayVehicleList(vehicles);

        } catch (Exception e) {
            ConsoleUI.printError("Failed to search vehicles: " + e.getMessage());
        }
    }

    private void sortByPrice() {
        ConsoleUI.printHeader("VEHICLES SORTED BY PRICE");

        try {
            List<Vehicle> vehicles = vehicleService.sortByPrice();

            if (vehicles == null || vehicles.isEmpty()) {
                ConsoleUI.printInfo("No vehicles found.");
                return;
            }

            displayVehicleList(vehicles);

        } catch (Exception e) {
            ConsoleUI.printError("Failed to sort vehicles: " + e.getMessage());
        }
    }

    private void displayVehicleList(List<Vehicle> vehicles) {
        ConsoleUI.printSeparator();
        for (Vehicle vehicle : vehicles) {
            System.out.println("\nID: " + vehicle.getId());
            System.out.println("Registration: " + vehicle.getRegistrationNumber());
            System.out.println("Brand: " + vehicle.getBrand());
            System.out.println("Model: " + vehicle.getModel());
            System.out.println("Type: " + vehicle.getType());
            System.out.println("Price/Day: ₹" + vehicle.getPricePerDay());
            System.out.println("Status: " + vehicle.getVehicleStatus());
            ConsoleUI.printSeparator();
        }
    }
}

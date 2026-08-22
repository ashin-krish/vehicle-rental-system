package com.ashin.vehiclerental.ui;

import com.ashin.vehiclerental.exception.DataAccessException;
import com.ashin.vehiclerental.model.Rental;
import com.ashin.vehiclerental.service.RentalService;
import java.util.List;
import java.util.Scanner;

public class RentalUI {

    private RentalService rentalService;
    private Scanner scanner;

    public RentalUI(Scanner scanner) {
        this.scanner = scanner;
        this.rentalService = new RentalService();
    }

    public void displayRentalMenu() {
        boolean running = true;

        while (running) {
            ConsoleUI.printHeader("RENTAL MANAGEMENT");
            ConsoleUI.printMenu(
                    "Rent Vehicle",
                    "Return Vehicle",
                    "View All Rentals",
                    "View Active Rentals",
                    "Back"
            );

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    rentVehicle();
                    break;
                case "2":
                    returnVehicle();
                    break;
                case "3":
                    viewAllRentals();
                    break;
                case "4":
                    viewActiveRentals();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    ConsoleUI.printError("Invalid choice. Please try again.");
            }
            ConsoleUI.printEmptyLine();
        }
    }

    private void rentVehicle() {
        ConsoleUI.printHeader("RENT VEHICLE");

        try {
            System.out.print("Enter customer ID: ");
            String customerIdInput = scanner.nextLine().trim();
            int customerId;

            try {
                customerId = Integer.parseInt(customerIdInput);
                if (customerId <= 0) {
                    ConsoleUI.printError("Customer ID must be greater than 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Customer ID must be a valid integer.");
                return;
            }

            System.out.print("Enter vehicle ID: ");
            String vehicleIdInput = scanner.nextLine().trim();
            int vehicleId;

            try {
                vehicleId = Integer.parseInt(vehicleIdInput);
                if (vehicleId <= 0) {
                    ConsoleUI.printError("Vehicle ID must be greater than 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Vehicle ID must be a valid integer.");
                return;
            }

            rentalService.rentVehicle(customerId, vehicleId);
            ConsoleUI.printSuccess("Vehicle rented successfully!");

        } catch (IllegalArgumentException e) {
            ConsoleUI.printError(e.getMessage());
        } catch (DataAccessException e) {
            ConsoleUI.printError("Database error: " + e.getMessage());
        } catch (Exception e) {
            ConsoleUI.printError("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void returnVehicle() {
        ConsoleUI.printHeader("RETURN VEHICLE");

        try {
            System.out.print("Enter customer ID: ");
            String customerIdInput = scanner.nextLine().trim();
            int customerId;

            try {
                customerId = Integer.parseInt(customerIdInput);
                if (customerId <= 0) {
                    ConsoleUI.printError("Customer ID must be greater than 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Customer ID must be a valid integer.");
                return;
            }

            System.out.print("Enter vehicle ID: ");
            String vehicleIdInput = scanner.nextLine().trim();
            int vehicleId;

            try {
                vehicleId = Integer.parseInt(vehicleIdInput);
                if (vehicleId <= 0) {
                    ConsoleUI.printError("Vehicle ID must be greater than 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Vehicle ID must be a valid integer.");
                return;
            }

            rentalService.returnRental(customerId, vehicleId);
            ConsoleUI.printSuccess("Vehicle returned successfully!");

        } catch (IllegalArgumentException e) {
            ConsoleUI.printError(e.getMessage());
        } catch (DataAccessException e) {
            ConsoleUI.printError("Database error: " + e.getMessage());
        } catch (Exception e) {
            ConsoleUI.printError("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void viewAllRentals() {
        ConsoleUI.printHeader("ALL RENTALS");

        try {
            List<Rental> rentals = rentalService.getAllRentals();

            if (rentals == null || rentals.isEmpty()) {
                ConsoleUI.printInfo("No rentals found.");
                return;
            }

            displayRentalList(rentals);

        } catch (Exception e) {
            ConsoleUI.printError("Failed to retrieve rentals: " + e.getMessage());
        }
    }

    private void viewActiveRentals() {
        ConsoleUI.printHeader("ACTIVE RENTALS");

        try {
            List<Rental> rentals = rentalService.getActiveRentals();

            if (rentals == null || rentals.isEmpty()) {
                ConsoleUI.printInfo("No active rentals found.");
                return;
            }

            displayRentalList(rentals);

        } catch (Exception e) {
            ConsoleUI.printError("Failed to retrieve active rentals: " + e.getMessage());
        }
    }

    private void displayRentalList(List<Rental> rentals) {
        ConsoleUI.printSeparator();
        for (Rental rental : rentals) {
            System.out.println("\nID: " + rental.getId());
            System.out.println("Customer ID: " + rental.getCustomerId());
            System.out.println("Vehicle ID: " + rental.getVehicleId());
            System.out.println("Rental Date: " + rental.getRentalDate());
            System.out.println("Return Date: " + 
                    (rental.getReturnDate() != null ? rental.getReturnDate() : "Not Yet Returned"));
            ConsoleUI.printSeparator();
        }
    }
}

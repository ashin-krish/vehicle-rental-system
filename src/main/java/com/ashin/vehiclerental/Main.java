package com.ashin.vehiclerental;

import com.ashin.vehiclerental.ui.ConsoleUI;
import com.ashin.vehiclerental.ui.CustomerUI;
import com.ashin.vehiclerental.ui.VehicleUI;
import com.ashin.vehiclerental.ui.RentalUI;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            ConsoleUI.printHeader("VEHICLE RENTAL SYSTEM");
            ConsoleUI.printMenu(
                    "Customer Management",
                    "Vehicle Management",
                    "Rental Management",
                    "Exit"
            );

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    CustomerUI customerUI = new CustomerUI(scanner);
                    customerUI.displayCustomerMenu();
                    break;
                case "2":
                    VehicleUI vehicleUI = new VehicleUI(scanner);
                    vehicleUI.displayVehicleMenu();
                    break;
                case "3":
                    RentalUI rentalUI = new RentalUI(scanner);
                    rentalUI.displayRentalMenu();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    ConsoleUI.printError("Invalid choice. Please try again.");
            }
            ConsoleUI.printEmptyLine();
        }

        scanner.close();
        ConsoleUI.printGoodbye();
    }
}

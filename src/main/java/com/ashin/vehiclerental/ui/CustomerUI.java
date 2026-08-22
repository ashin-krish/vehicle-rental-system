package com.ashin.vehiclerental.ui;

import com.ashin.vehiclerental.model.Customer;
import com.ashin.vehiclerental.repository.CustomerRepository;
import com.ashin.vehiclerental.service.CustomerService;
import java.util.List;
import java.util.Scanner;

public class CustomerUI {

    private CustomerService customerService;
    private Scanner scanner;

    public CustomerUI(Scanner scanner) {
        this.scanner = scanner;
        CustomerRepository customerRepository = new CustomerRepository();
        this.customerService = new CustomerService(customerRepository);
    }

    public void displayCustomerMenu() {
        boolean running = true;

        while (running) {
            ConsoleUI.printHeader("CUSTOMER MANAGEMENT");
            ConsoleUI.printMenu(
                    "Register Customer",
                    "View Customers",
                    "Back"
            );

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    registerCustomer();
                    break;
                case "2":
                    viewCustomers();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    ConsoleUI.printError("Invalid choice. Please try again.");
            }
            ConsoleUI.printEmptyLine();
        }
    }

    private void registerCustomer() {
        ConsoleUI.printHeader("REGISTER CUSTOMER");

        try {
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                ConsoleUI.printError("Name cannot be empty.");
                return;
            }

            System.out.print("Enter customer email: ");
            String email = scanner.nextLine().trim();

            if (email.isEmpty()) {
                ConsoleUI.printError("Email cannot be empty.");
                return;
            }

            System.out.print("Enter customer phone: ");
            String phone = scanner.nextLine().trim();

            if (phone.isEmpty()) {
                ConsoleUI.printError("Phone cannot be empty.");
                return;
            }

            Customer customer = new Customer(name, email, phone);
            customerService.registerCustomer(customer);
            ConsoleUI.printSuccess("Customer registered successfully!");

        } catch (IllegalArgumentException e) {
            ConsoleUI.printError(e.getMessage());
        } catch (Exception e) {
            ConsoleUI.printError("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void viewCustomers() {
        ConsoleUI.printHeader("VIEW CUSTOMERS");

        try {
            CustomerRepository customerRepository = new CustomerRepository();
            List<Customer> customers = customerRepository.getAllCustomers();

            if (customers == null || customers.isEmpty()) {
                ConsoleUI.printInfo("No customers found.");
                return;
            }

            ConsoleUI.printSeparator();
            for (Customer customer : customers) {
                System.out.println("\nID: " + customer.getId());
                System.out.println("Name: " + customer.getName());
                System.out.println("Email: " + customer.getEmail());
                System.out.println("Phone: " + customer.getPhone());
                ConsoleUI.printSeparator();
            }

        } catch (Exception e) {
            ConsoleUI.printError("Failed to retrieve customers: " + e.getMessage());
        }
    }
}

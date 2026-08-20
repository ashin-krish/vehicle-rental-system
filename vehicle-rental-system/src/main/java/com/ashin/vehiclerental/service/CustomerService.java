package com.ashin.vehiclerental.service;

import com.ashin.vehiclerental.repository.CustomerRepository;
import com.ashin.vehiclerental.model.Customer;

public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(Customer customer) {

        String EMAIL_REGEX =
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!customer.getEmail().matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid Email Format");
        }

        boolean customerEmailStatus =
                customerRepository.existByEmail(customer.getEmail());

        if (customerEmailStatus) {
            throw new IllegalArgumentException("Email Already Exists");
        }

        customerRepository.saveCustomer(customer);
    }

    public boolean existById(int customerId) {
        return customerRepository.existsById(customerId);
    }
}
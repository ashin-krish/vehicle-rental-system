package com.ashin.vehiclerental.service;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.ashin.vehiclerental.model.Customer;
import com.ashin.vehiclerental.repository.CustomerRepository;


public class CustomerServiceTest 
{
    @Test
    void customerExistValid()
    {
        CustomerRepository mockRepository = mock(CustomerRepository.class);

        when(mockRepository.existsById(1)).thenReturn(true);
        

        CustomerService customerService = new CustomerService(mockRepository);

        boolean result = customerService.existById(1);

        assertTrue(result);

    }

    @Test
    void customerExistInvalid()
    {
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);

        when(mockCustomerRepository.existsById(1)).thenReturn(false);
        CustomerService customerService = new CustomerService(mockCustomerRepository);

        boolean result = customerService.existById(1);

        assertFalse(result);
    }


    @Test
    void CustomerRegisterValid()
    {
       CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);

        when(mockCustomerRepository.existByEmail("ash@gmail.com")).thenReturn(false);

        CustomerService customerService = new CustomerService(mockCustomerRepository);


        Customer customer = new Customer("Ashin", "ash@gmail.com", "1234567890");

        customerService.registerCustomer(customer);

        verify(mockCustomerRepository).saveCustomer(customer);


    }

    @Test
    void registerCustomerInvalid()
    {
        CustomerRepository mockCustomerRepository =  mock(CustomerRepository.class);

        when(mockCustomerRepository.existByEmail("ash@gmail.com")).thenReturn(true);

        CustomerService customerService = new CustomerService(mockCustomerRepository);

        Customer customer = new Customer("Ashin", "ash@gmail.com", "0987654321");

        assertThrows(IllegalArgumentException.class, () -> customerService.registerCustomer(customer));

       verify(mockCustomerRepository,never()).saveCustomer(customer);

    }

    @Test
    void registerCustomerInavlidEmail()
    {
        CustomerRepository customerRepository = mock(CustomerRepository.class);

        CustomerService customerService = new CustomerService(customerRepository);

        Customer customer = new Customer("Ashin", "ash", "09876543212");

        assertThrows(IllegalArgumentException.class,() ->customerService.registerCustomer(customer));

        verify(customerRepository,never()).saveCustomer(any(Customer.class));
   
 
    }


}

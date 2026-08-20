package com.ashin.vehiclerental.service;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.ashin.vehiclerental.repository.CustomerRepository;
import com.ashin.vehiclerental.service.CustomerService;

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
}


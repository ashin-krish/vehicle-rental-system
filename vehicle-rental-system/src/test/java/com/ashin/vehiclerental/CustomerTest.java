package com.ashin.vehiclerental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.ashin.vehiclerental.model.Customer;

public class CustomerTest 
{

    

     @Test
    void checkCustomerValid()
    {
        Customer customer = new Customer("Ashin", "ash@gmail.com", "1234567890");

        assertEquals("Ashin", customer.getName());
        assertEquals("ash@gmail.com", customer.getEmail());
        assertEquals("1234567890", customer.getPhone());
    }

    @Test
    void checkCustomerValidSecond()
    {
        Customer customer = new Customer(1,"Ashin", "ash@gmail.com", "1234567890");

        assertEquals(1, customer.getId());
        assertEquals("Ashin", customer.getName());
        assertEquals("ash@gmail.com", customer.getEmail());
        assertEquals("1234567890", customer.getPhone());
    }

    @Test
    void checkCustomerInValid()
    {
        Customer customer = new Customer("Ak", "ash@gmail.com", "1234567890");

        assertThrows(IllegalArgumentException.class, () -> customer.setEmail(""));
        assertThrows(IllegalArgumentException.class, () -> customer.setName(""));
        assertThrows(IllegalArgumentException.class, () -> customer.setPhone(""));
    }

    
}

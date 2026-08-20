package com.ashin.vehiclerental.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;



public class CustomerTest 
{
  
    @Test
    void customerValid()
    {
        Customer customer = new Customer("Ashin", "ash@gmail.com", "1234567890");

        assertEquals("Ashin",customer.getName());
        assertEquals("ash@gmail.com",customer.getEmail());
        assertEquals("1234567890",customer.getPhone());


    }

    @Test
    void customerCheckValidTwo()
    {
        Customer customer = new Customer(1,"Ashin", "ash@gmail.com", "1234567890");

        assertEquals(1, customer.getId());
        assertEquals("Ashin", customer.getName());
        assertEquals("ash@gmail.com", customer.getEmail());
        assertEquals("1234567890", customer.getPhone());

    }

   @Test
void invalidName()
{
    assertThrows(
        IllegalArgumentException.class,
        () -> new Customer("", "ash@gmail.com", "1234567890")
    );
}

@Test
void invalidEmail()
{
    assertThrows(
        IllegalArgumentException.class,
        () -> new Customer("Ashin", "", "1234567890")
    );
}

@Test
void invalidPhone()
{
    assertThrows(
        IllegalArgumentException.class,
        () -> new Customer("Ashin", "ash@gmail.com", null)
    );
}
}

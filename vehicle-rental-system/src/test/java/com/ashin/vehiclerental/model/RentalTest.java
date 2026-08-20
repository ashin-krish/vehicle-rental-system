package com.ashin.vehiclerental.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class RentalTest 
{
    @Test
    void ValidRental()
    {
        LocalDate localDate = LocalDate.now();
        Rental rental = new Rental(1, 1, localDate, localDate);

        assertEquals(1, rental.getCustomerId());
        assertEquals(1, rental.getVehicleId());
        assertEquals(LocalDate.now(), rental.getRentalDate());
        assertEquals(LocalDate.now(), rental.getReturnDate());
    } 
    
    @Test
    void rentalInvalidCustomerId()
    {
        assertThrows(IllegalArgumentException.class, () -> new Rental(-1, 1, LocalDate.now(), LocalDate.now()));
    }

    @Test
    void rentalInvalidVehicleId()
    {
        assertThrows(IllegalArgumentException.class, () -> new Rental(1, -11, LocalDate.now(), LocalDate.now()));
    }

    @Test
    void rentalInvalidRentalDate()
    {
        assertThrows(IllegalArgumentException.class, () -> new Rental(1, 11, null, LocalDate.now()));
    }



}

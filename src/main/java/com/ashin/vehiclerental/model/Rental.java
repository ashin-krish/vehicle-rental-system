package com.ashin.vehiclerental.model;

import java.time.LocalDate;

public class Rental 
{
    

    private int id;
    private int customerId;
    private int vehicleId;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public Rental(int customerId, int vehicleId, LocalDate rentalDate, LocalDate returnDate)
    {
        setCustomerId(customerId);
        setVehicleId(vehicleId);
        setRentalDate(rentalDate);
        setReturnDate(returnDate);
    }

    public Rental(int id, int customerId, int vehicleId, LocalDate rentalDate, LocalDate returnDate)
    {
        this.id = id;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        if (id <= 0)
        {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        this.id = id;
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        if (customerId <= 0)
        {
            throw new IllegalArgumentException("Customer ID must be greater than 0");
        }
        this.customerId = customerId;
    }

    public int getVehicleId()
    {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId)
    {
        if (vehicleId <= 0)
        {
            throw new IllegalArgumentException("Vehicle ID must be greater than 0");
        }
        this.vehicleId = vehicleId;
    }

    public LocalDate getRentalDate()
    {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate)
    {
        if (rentalDate == null)
        {
            throw new IllegalArgumentException("Rental date cannot be null");
        }
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate()
    {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate)
    {
    
        this.returnDate = returnDate;
    }

    @Override
    public String toString()
    {
        return "Rental{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", vehicleId=" + vehicleId +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                '}';
    }
}

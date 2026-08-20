package com.ashin.vehiclerental.model;

public class Vehicle 
{

    public enum VehicleStatus
    {
        AVAILABLE,
        RENTED,
        MAINTENANCE
    }

     private int id;
     private String registrationNumber;
     private String brand;
     private String model;
     private String type;
     private int pricePerDay;
     private VehicleStatus vehicleStatus;
   

    public Vehicle(String registrationNumber,String brand,String model,String type,int pricePerDay,VehicleStatus vehicleStatus)
     {
        setRegistrationNumber(registrationNumber);
        setBrand(brand);
        setModel(model);
        setType(type);
        setPricePerDay(pricePerDay);
        setVehicleStatus(vehicleStatus);
    
     }


    public Vehicle(int id,String registrationNumber,String brand,String model,String type,int pricePerDay,VehicleStatus vehicleStatus)
     {

       this.id=id;
        this.registrationNumber=registrationNumber;
        this.brand=brand;
        this.model=model;  
        this.type=type;
         this.pricePerDay=pricePerDay;
         this.vehicleStatus=vehicleStatus;
   
     }

     public void setRegistrationNumber(String registrationNumber)
     {
        if(registrationNumber == null || registrationNumber.trim().isEmpty())
        {
            throw new IllegalArgumentException();
        }

        this.registrationNumber=registrationNumber;
     }

     public void setBrand(String brand)
     {
        if(brand == null || brand.trim().isEmpty())
        {
            throw new IllegalArgumentException();
        }

        this.brand=brand;
     }

     public void setModel(String model)
     {
        if(model==null || model.trim().isEmpty())
        {
            throw new IllegalArgumentException();
        }

        this.model=model;
     }

     public void setType(String type)
     {
        if(type==null || type.trim().isEmpty())
        {
            throw new IllegalArgumentException();
        }

        this.type=type;
     }

     public void setPricePerDay(int pricePerDay)
     {
        if(pricePerDay < 0)
        {
            throw new IllegalArgumentException();
        }

        this.pricePerDay=pricePerDay;
     }

    public void setVehicleStatus(VehicleStatus vehicleStatus)
    {
        if(vehicleStatus == null)
        {
            throw new IllegalArgumentException();
        }

            this.vehicleStatus=vehicleStatus;

    }

    
     public String getRegistrationNumber()
     {
        return registrationNumber;
     }

     public String getBrand()
     {
        return brand;
     }

     public String getModel()
     {
        return model;
     }

     public String getType()
     {

        return type;
     }

     public int getPricePerDay()
     {
        return pricePerDay;
     }

     public int getId()
     {
        return id;
     }

     public VehicleStatus getVehicleStatus()
     {
        return vehicleStatus;
     }


     @Override
public String toString()
{
    return "Vehicle{" +
            "id=" + id +
            ", registrationNumber='" + registrationNumber + '\'' +
            ", brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", type='" + type + '\'' +
            ", pricePerDay=" + pricePerDay +
            ", vehicleStatus=" + vehicleStatus +
            '}';
}

}

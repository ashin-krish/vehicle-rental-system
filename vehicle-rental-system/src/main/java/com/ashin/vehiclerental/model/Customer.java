package com.ashin.vehiclerental.model;

public class Customer 
{
    private int id;
    private String name;
    private String email;
    private String phone;

   public Customer(int id,String email,String phone,String name)
    {
       this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;

    }

    public Customer(String name,String email,String phone)
    {
        setName(name);
        setEmail(email);
        setPhone(phone); 
    }

    public void setName(String name)
    {
        if(name.isEmpty())
        {
            throw new IllegalArgumentException();
        }

        this.name=name;
    }

    public void setEmail(String email)
    {
        if( email == null ||email.trim().isEmpty())
        {
            throw new IllegalArgumentException();
        }

        this.email=email;
    }

    public void setPhone(String phone)
    {
        if(phone == null || phone.trim().isEmpty() || phone.length() < 10)
        {
            throw new IllegalArgumentException();
        }

        this.phone=phone;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhone()
    {
        return phone;
    }

}

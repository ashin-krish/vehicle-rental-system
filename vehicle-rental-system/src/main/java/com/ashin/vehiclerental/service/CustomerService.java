package com.ashin.vehiclerental.service;

import com.ashin.vehiclerental.repository.CustomerRepository;
// import java.util.List;

import com.ashin.vehiclerental.model.Customer; 

public class CustomerService 
{
   private CustomerRepository customerRepository = new CustomerRepository();    

  

    public void registerCustomer(Customer customer)
    {
           String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";


           if(!customer.getEmail().matches(EMAIL_REGEX))
          {
              throw new IllegalArgumentException("Inavlid Email Format");
          }

       
            boolean customerEmailStatus = customerRepository.existByEmail(customer.getEmail());


            if(customerEmailStatus)
            {
                throw new IllegalArgumentException(" Email Already Exist ");
            }

            customerRepository.saveCustomer(customer);



    }
}
 
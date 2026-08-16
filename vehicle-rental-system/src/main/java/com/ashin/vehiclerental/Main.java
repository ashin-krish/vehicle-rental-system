package com.ashin.vehiclerental;

import java.util.ArrayList;
import java.util.List;

import com.ashin.vehiclerental.model.Customer;
import com.ashin.vehiclerental.repository.CustomerRepository;
import com.ashin.vehiclerental.service.CustomerService;

public class Main 
{

       public static void main(String[] args) 
       {

           CustomerRepository customerRepository = new CustomerRepository();

           CustomerService customerService = new CustomerService();
           
           
        //    Customer customer = new Customer("Ashin", "ash@gmail.com", "1234567890");
           
        //    customerService.registerCustomer(customer);
           
              List<Customer> customers = new ArrayList<>();
   
              customers = customerRepository.getAllCustomers();
   
              for (Customer customer2 : customers) 
            {
               System.out.println(customer2);
              }
        

       


       }
}

package com.ashin.vehiclerental.util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection 
{

        private  String url;
        private  String user;
        private  String password;
      
            
            
          public  DatabaseConnection() throws IOException
            {
                  try (InputStream inputStream =
                      getClass().getClassLoader()
                              .getResourceAsStream("db.properties")) 
                {

                  Properties properties = new Properties();
                  properties.load(inputStream);
                  url = properties.getProperty("url");
            
                  user = properties.getProperty("user");
            
                  password = properties.getProperty("password");
              }

      
        
            }
      

     

     public Connection getConnection() throws SQLException
     {
        return DriverManager.getConnection(url,user,password);
     }

        
}


    


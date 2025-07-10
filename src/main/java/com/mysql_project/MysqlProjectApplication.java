package com.mysql_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

// This is the main class for the Spring Boot application
// It initializes the application and sets up a connection to a MySQL database
@SpringBootApplication
public class MysqlProjectApplication {

    public static void main(String[] args)throws Exception {
        SpringApplication.run(MysqlProjectApplication.class, args);

        // JDBC URL, username and password of MySQL database
        String url = "jdbc:mysql://localhost:3306/mysql_project";
        String user = "root";
        String password = "admin";

        // Register the class for the MySQL driver and create a connection
        
            Class.forName("com.mysql.cj.jdbc.Driver");
            // connectio to the database
            Connection con = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO `mysql_project`.`employee`  VALUES (101, 'sagar', 'j', 'w@123', '12345', 'pune');";
            // Statement interface is used to execute SQL queries
            Statement stmt = con.createStatement();
            // Execute the SQL query
            // stmt.executeUpdate(sql);
            int count = stmt.executeUpdate(sql);
            if (count > 0) {
                // If the count is greater than 0, it means the data was inserted successfully
                System.out.println("Data inserted successfully");
                System.out.println("Connection successful and data inserted!");
            } else {
                // If the count is 0, it means no data was inserted
                System.out.println("data insertion failed:");
            }

         
            System.out.println("Connection failed!");
            //e.printStackTrace(); // printStackTrace() : - that means it will print the error message and stack
                                 // trace to the console
            // e. :- means it will print the error message to the console
        

    }
}
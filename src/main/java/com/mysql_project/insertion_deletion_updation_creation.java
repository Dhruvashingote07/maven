package com.mysql_project;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
// Importing necessary classes for Spring Boot application
import org.springframework.boot.SpringApplication;


// Importing necessary classes for Spring Boot application

public class insertion_deletion_updation_creation {

    public static void main(String[] args) {
        SpringApplication.run(MysqlProjectApplication.class, args);

        // JDBC URL, username and password of MySQL database
        String url = "jdbc:mysql://localhost:3306/mysql_project";
        String user = "root";
        String password = "admin";

        
        // Register the class for the MySQL driver and create a connection
        try {
            System.out.println("===================================================================");
            System.out.println("-------------------------------------------------------------------");

            System.out.println("Hey Welcome to the MySQL Project!");
            System.out.println("Creating table, inserting data, deleting table and updating data ...");
            System.out.println("--------- MOST SAFEST DATABASE --------");
            System.out.println("");
            Scanner sc = new Scanner(System.in);
            try {

                do {

                    System.out.println("ENTER YOUR CHOICE");
                    System.out.println("0. Select Data");
                    System.out.println("1. Create Table");
                    System.out.println("2. Insert Data");
                    System.out.println("3. Update Data");
                    System.out.println("4. Delete Data");
                    System.out.println("5. Exit");
                    int ch = sc.nextInt();

                    switch (ch) {
                        case 0:
                            SeleteTable(url, user, password);
                            break;
                        case 1:
                            CreateTable(url, user, password);
                            break;
                        case 2:
                            InsertTable(url, user, password);
                            break;
                        case 3:
                            UpdateTable(url, user, password);
                            break;
                        case 4:
                            DeleteTable(url, user, password);
                            break;
                        case 5:
                            System.out.println("Exiting...");
                            return;
                            /*Here are all the common cases where we use return in Java:

                                1.To exit a method early:
                                    - Stops the method and returns control to the caller. 
                                    - In void methods (like your main), it just ends the method.

                                2.To return a value from a method:
                                        - In non-void methods, return sends a value back to the caller. 
                                        - Example: return 5; in a method with int return type.

                                3.To break out of loops or conditional logic inside a method:
                                        - If a certain condition is met, return can be used to 
                                            stop further execution of the method.
                                        In your code, return is used to exit the main method 
                                        and end the program when the user chooses "Exit". */
                        default:
                            System.out.println("Invalid choice! Please enter a valid option.");
                            break;

                    }
                    System.out.println("-------------------------------------------------------------------");
                } while (true);
            } finally {
                sc.close();
            }

        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace(); // printStackTrace() : - that means it will print the error message and stack
                                 // trace to the console
            // e. :- means it will print the error message to the console
        }

    }

    private static void CreateTable(String url, String user, String password) throws SQLException, Exception {
        try{ 
        
            String sql = "CREATE TABLE `mysql_project`.`DHRUV` (" +
                "emp_id INT PRIMARY KEY, " +
                "first_name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "email VARCHAR(100), " +
                "mobile VARCHAR(20), " +
                "address VARCHAR(255)" +
                ")";
        PreparedStatement ps = con(url, user, password).prepareStatement(sql);
        ps.executeUpdate();

        System.out.println("Table created successfully");
        ps.close();
    }catch(Exception e){

        System.out.println("Error creating table: ");

        }
    }

    private static void InsertTable(String url, String user, String password) throws Exception {

        String sql = "INSERT INTO `mysql_project`.`DHRUV`  VALUES (?,?,?,?,?,?);";
        PreparedStatement ps = con(url, user, password).prepareStatement(sql);
        
        
        ps.setString(1, "10");// row 1 column 1
        ps.setString(2, "hello");// row 1 column 2
        ps.setString(3, "hello");// row 1 column 3
        ps.setString(4, "hello");// row 1 column 4
        ps.setString(5, "hello");// row 1 column 5
        ps.setString(6, "hello");// row 1 column 6
        ps.executeUpdate();
        System.out.println("Rows inserted: " + ps);
        System.out.println("PreparedStatement created successfully");
        ps.close();

        System.out.println("Connection closed successfully");
    }

    private static void UpdateTable(String url, String user, String password) throws Exception {
        
        String sql = "UPDATE `mysql_project`.`DHRUV` SET first_name = ?, lastname = ?, email = ?, mobile = ?, address = ? WHERE emp_id = ?;";
        PreparedStatement ps = con(url, user, password).prepareStatement(sql);
        
        
        ps.setString(1, "newFirstName"); // new first_name
        ps.setString(2, "newLastName"); // new lastname
        ps.setString(3, "newEmail"); // new email
        ps.setString(4, "newMobile"); // new mobile
        ps.setString(5, "newAddress"); // new address
        ps.setString(6, "10"); // emp_id to update
        int rowsUpdated = ps.executeUpdate();
        System.out.println("Rows updated: " + rowsUpdated);
        System.out.println("PreparedStatement for update executed successfully");
        ps.close();
        System.out.println("Connection closed successfully");
    }

    private static void DeleteTable(String url, String user, String password) throws Exception {
      
        String sql = "DELETE FROM `mysql_project`.`DHRUV` WHERE emp_id = ?;";
        PreparedStatement ps = con(url, user, password).prepareStatement(sql);
        ps.setString(1, "10");
        int rowsDeleted = ps.executeUpdate();
        System.out.println("Rows deleted: " + rowsDeleted);
        ps.close();
        System.out.println("Connection closed successfully");
    }
     private static void SeleteTable(String url, String user, String password) throws Exception {
        String sql ="SELECT * FROM `mysql_project`.`DHRUV`;";
        PreparedStatement ps = con(url, user, password).prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
           
            System.out.println("emp_id: " + rs.getString("emp_id"));
            System.out.println("first_name: " + rs.getString("first_name"));
            System.out.println("lastname: " + rs.getString("lastname"));
            System.out.println("email: " + rs.getString("email"));
            System.out.println("mobile: " + rs.getString("mobile"));
            System.out.println("address: " + rs.getString("address"));
            System.out.println("-----------------------------");
        }
        rs.close();
        System.out.println("Data selected successfully");   
        ps.close();
        System.out.println("Connection closed successfully");
    }

    private static Connection con(String url, String user, String password) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        return con;

    }

};
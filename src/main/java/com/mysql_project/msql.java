package com.mysql_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class msql {

    public static void main(String[] args) throws Exception {
        getPassword(args);
    }

    private static void getPassword(String[] args) throws IOException {
        SpringApplication.run(MysqlProjectApplication.class, args);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the Emp_id");
        String emp_id = br.readLine();
        System.out.println("Enter the First Name");
        String first_name = br.readLine();
        System.out.println("Enter the Last Name");
        String lastname = br.readLine();
        System.out.println("Enter the Email");
        String email = br.readLine();
        System.out.println("Enter the Mobile");
        String mobile = br.readLine();
        System.out.println("Enter the Address");
        String address = br.readLine();

        String url = "jdbc:mysql://localhost:3306/mysql_project";
        String username = "root";
        String password = "admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO employee (emp_id, first_name, lastname, email, mobile, address) VALUES ('"
                    + emp_id + "', '" + first_name + "', '" + lastname + "', '" + email + "', '" + mobile + "', '"
                    + address + "')";
            int rowsInserted = stmt.executeUpdate(sql);
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully");
            } else {
                System.out.println("Data insertion failed");
            }

            String select = "SELECT * FROM employee";// creating a statement to select all records from employee table
            // Execute the SQL query
            ResultSet r = stmt.executeQuery(select);
            // ResultSet r = con.createStatement().executeQuery("SELECT * FROM employee");
            while (r.next()) {
                System.out.println("emp_id: " + r.getString("emp_id"));
                System.out.println("first_name: " + r.getString("first_name"));
                System.out.println("lastname: " + r.getString("lastname"));
                System.out.println("email: " + r.getString("email"));
                System.out.println("mobile: " + r.getString("mobile"));
                System.out.println("address: " + r.getString("address"));
                System.out.println("-----------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection failed!");
        }
    }
}

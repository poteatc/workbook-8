package com.tutorialspoint.example;

import java.sql.*;

public class UpdateExample {
    static final String DB_URL = "jdbc:mysql://localhost/TUTORIALSPOINT";
    static final String USER = "root";
    static final String PASS = "yearup";
    static final String QUERY1 = "UPDATE Employees SET age=20 where id=100";
    static final String QUERY2= "SELECT id, first, last, age FROM Employees";

    public static void main (String [] args) {
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {

            stmt.executeUpdate(QUERY1);
            ResultSet rs=stmt.executeQuery(QUERY2);
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
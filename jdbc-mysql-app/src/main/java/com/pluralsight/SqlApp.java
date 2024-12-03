package com.pluralsight;

import java.sql.*;

public class SqlApp {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost/dealership";
        String user = "root";
        String password = "yearup";


        // Try-with-resources example:
        try (Connection connection = DriverManager.getConnection(url, user, password)) { // Calls close() if throws SQLException but resource must implement close() method

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT dealership_id, name, address, phone FROM dealerships");

            while (resultSet.next()) {
                System.out.println("Dealership ID: " + resultSet.getInt("dealership_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Address: " + resultSet.getString("address"));
                System.out.println("Phone #: " + resultSet.getString("phone"));
            }

            resultSet = statement.executeQuery("""
                    select dealerships.name, vehicles.make, vehicles.model, inventory.VIN
                    from vehicles
                    join inventory on inventory.VIN = vehicles.VIN
                    join dealerships on dealerships.dealership_id = inventory.dealership_id
                    where inventory.dealership_id = 1;
                    """);

            //System.out.println("Dealership ID: 1");
            while (resultSet.next()) {
                System.out.println("Dealership Name: " + resultSet.getString("dealerships.name"));
                System.out.println("Vehicle:");
                System.out.println("Make: " + resultSet.getString("vehicles.make"));
                //System.out.println();
            }
            //resultSet.close();
            //connection.close(); // Tidy up
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
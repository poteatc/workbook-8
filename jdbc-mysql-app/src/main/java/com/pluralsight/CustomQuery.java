package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class CustomQuery {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost/dealership";
        String user = "root";
        String password = "yearup";

        Scanner scanner = new Scanner((System.in));

        boolean running = true;
        while (running) {

//            System.out.println("Enter a SQL query: ");
            System.out.println("Enter the dealership ID #: ");

            String command = scanner.nextLine();

            // Try-with-resources example: // problem with this is that new connection is made each iteration.. can fix by surrounding while loop with try/catch instead of the way shown here
            try (Connection connection = DriverManager.getConnection(url, user, password)) { // Calls close() if throws SQLException but resource must implement close() method


//                Statement statement = connection.createStatement();
//                statement.executeQuery(command);
//                //ResultSet resultSet = statement.executeQuery(command);
//
////                // execute several updates
////                connection.setAutoCommit(false);
////                connection.commit();
//
//                ResultSet resultSet = statement.getResultSet();

                // PreparedStatement

                PreparedStatement statement = connection.prepareStatement("""
                    select dealerships.name, vehicles.make, vehicles.model, inventory.VIN
                    from vehicles
                    join inventory on inventory.VIN = vehicles.VIN
                    join dealerships on dealerships.dealership_id = inventory.dealership_id
                    where inventory.dealership_id = ?;
                
                """);
                /*
                • Once you’ve built the prepared statement, you must
                  provide values for your parameters
                        • To add the value for that parameter, use
                        the PreparedStatement object, and call setXXX(),
                        where XXX refers to the type you wish to set, including:
                            - setString()
                            - setInt()
                            - setDouble()
                            - setDate()
                            - setBlob()
                    • IMPORTANT: Parameters are identified using a
                    1-based counting system
                 */
                statement.setString(1, command);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {
                    System.out.println("Dealership Name: " + resultSet.getString("dealerships.name"));
                    System.out.println("Vehicle:");
                    System.out.println("Make: " + resultSet.getString("vehicles.make"));
//                    System.out.println("Dealership ID: " + resultSet.getInt("dealership_id"));
//                    System.out.println("Name: " + resultSet.getString("name"));
//                    System.out.println("Address: " + resultSet.getString("address"));
//                    System.out.println("Phone #: " + resultSet.getString("phone"));
                   // System.out.println("Make: " + resultSet.getString("make"));
                }

                //resultSet.close();
                //connection.close(); // Tidy up
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
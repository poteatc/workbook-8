package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Scanner;

public class DataSourceExample {
    public static void main(String[] args) throws SQLException {

        BasicDataSource dataSource = new BasicDataSource();
        String url = "jdbc:mysql://localhost/dealership";
//        String user = "root";
//        String password = "yearup";
        dataSource.setUrl(url);
        dataSource.setUsername(args[0]);
        dataSource.setPassword(args[1]);

        Scanner scanner = new Scanner((System.in));

        boolean running = true;
        // Try-with-resources example: // problem with this is that new connection is made each iteration.. can fix by surrounding while loop with try/catch instead of the way shown here
        try (Connection connection = dataSource.getConnection()) { // Calls close() if throws SQLException but resource must implement close() method
            while (running) {

//            System.out.println("Enter a SQL query: ");
                System.out.println("Enter the dealership ID #: (or 'x' to exit)");

                String command = scanner.nextLine();

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

                if (command.trim().equalsIgnoreCase("x")) {
                    running = false;
                }
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dataSource.close();
    }
}

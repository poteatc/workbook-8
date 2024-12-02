package com.pluralsight;

import java.sql.*;

public class SqlApp {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost/dealership";
        String user = "root";
        String password = "yearup";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT dealership_id, name, address, phone FROM dealerships");

            while (resultSet.next()) {
                System.out.println("Dealership ID: " + resultSet.getInt("dealership_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Address: " + resultSet.getString("address"));
                System.out.println("Phone #: " + resultSet.getString("phone"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
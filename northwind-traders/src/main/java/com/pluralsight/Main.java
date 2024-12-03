package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        // load the MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/northwind";
        String user = "root";
        String password = "yearup";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            String query = "select * from products";

            //PreparedStatement statement = connection.prepareStatement();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.printf("Product Id: %d\n" +
                        "Name: %s\n" +
                        "Price: %.2f\n" +
                        "Stock: %d\n\n" +
                        "-------------------\n"
                        , rs.getInt("ProductID")
                        , rs.getString("ProductName")
                        , rs.getDouble("UnitPrice")
                        , rs.getInt("UnitsInStock"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//    public static void main(String[] args) throws ClassNotFoundException {
//        // load the MySQL Driver
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//        String url = "jdbc:mysql://localhost:3306/northwind";
//        String user = "root";
//        String password = "yearup";
//
//        try (Connection connection = DriverManager.getConnection(url, user, password)) {
//
//            Statement statement = connection.createStatement();
//
//            String query = "select * from products";
//
//            ResultSet rs = statement.executeQuery(query);
//
//            while (rs.next()) {
//                System.out.println(rs.getString("ProductName"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
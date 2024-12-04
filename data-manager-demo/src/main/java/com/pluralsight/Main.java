package com.pluralsight;

import com.pluralsight.dealership.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost/dealership");
        dataSource.setUsername(args[0]);
        dataSource.setPassword(args[1]);

        DataManager dataManager = new DataManager(dataSource);

        List<Vehicle> vehicles = dataManager.getAllVehicles();

        vehicles.forEach(System.out::println);
    }
}
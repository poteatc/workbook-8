package com.pluralsight;

import com.pluralsight.model.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost/dealership");
        dataSource.setUsername(args[0]);
        dataSource.setPassword(args[1]);

        VehicleDAOMySqlImpl dataManager = new VehicleDAOMySqlImpl(dataSource);
        //DealershipDAOMySqlImpl dealershipDAOMySql = new Dealer

        List<Vehicle> vehicles = dataManager.getAllVehicles();

        vehicles.forEach(System.out::println);
    }
}
package com.pluralsight;

import com.pluralsight.model.Vehicle;

import java.util.List;

public interface VehicleDAO {
    Vehicle findVehicleByVin();
    List<Vehicle> getAllVehicles();
}

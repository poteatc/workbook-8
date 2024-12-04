package com.pluralsight.dealership;

public class Vehicle {
    private final String vin;
    private final int year;
    private final String make;
    private final String model;
    private final String vehicleType;
    private final String color;
    private final int odometer;
    private final double price;
    private final boolean sold;

    public Vehicle(String vin, int year, String make, String model, String vehicleType, String color, int odometer, double price, boolean sold) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
        this.sold = sold;
    }

    public String getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }

    public double getPrice() {
        return price;
    }

    public boolean getSold() { return sold; }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-10s %-10d %-10s %-10s %-10d %10.2f",
                vin, make, model, year, vehicleType, color, odometer, price);
    }

    //int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price
    public String toCSVFormat() {
        return String.format("%s|%d|%s|%s|%s|%s|%d|%.2f",
                vin, year, make, model, vehicleType, color, odometer, price);
    }
}

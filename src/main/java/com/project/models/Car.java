package com.project.models;

public class Car {
    private int car_id;
    private String brand;
    private String path;

    public Car() {
    }

    public Car(int car_id, String brand, String path) {
        this.car_id = car_id;
        this.brand = brand;
        this.path = path;
    }

    public Car(String brand, String path) {
        this.brand = brand;
        this.path = path;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

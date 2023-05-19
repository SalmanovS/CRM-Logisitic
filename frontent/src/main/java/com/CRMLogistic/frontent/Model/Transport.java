package com.CRMLogistic.frontent.Model;

public class Transport {

    private int id;

    private String brand;

    private String model;

    private String number;

    private String bodyType;

    private double carryingCapacity;

    private String status;

    public Transport() {
    }

    public Transport(String brand, String model, String number, String bodyType, double carryingCapacity, String status) {
        this.brand = brand;
        this.model = model;
        this.number = number;
        this.bodyType = bodyType;
        this.carryingCapacity = carryingCapacity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public String getBodyType() {
        return bodyType;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public String getStatus() {
        return status;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", number='" + number + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", carryingCapacity=" + carryingCapacity +
                ", status='" + status + '\'' +
                '}';
    }
}

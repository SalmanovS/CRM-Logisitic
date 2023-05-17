package com.CRMLogistic.transportservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity(name = "transports")
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "number")
    private String number;
    @Column(name = "body_type")
    private String bodyType;
    @Column(name = "carrying_capacity")
    private double carryingCapacity;
    @Column(name = "status")
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

package com.CRMLogistic.orderservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "cargo_name")
    private String cargoName;
    @Column(name = "cargo_weight")
    private int weight;
    @Column(name = "departure_address")
    private String departureAddress;
    @Column(name = "destination_address")
    private String destinationAddress;
    @Column(name = "route_length")
    private int routeLength;
    @Column(name = "departure_date")
    private LocalDate departureDate;
    @Column(name = "destination_date")
    private LocalDate destinationDate;
    @Column(name = "order_price")
    private double orderPrice;
    @Column(name = "order_status")
    private String status;
    @Column(name = "transport_id")
    private int transportId;

    public Order() {
    }

    public Order(String orderNumber, String cargoName, int weight, String departureAddress, String destinationAddress,
                 int routeLength, LocalDate departureDate, LocalDate destinationDate, double orderPrice, int transportId) {
        this.orderNumber = orderNumber;
        this.cargoName = cargoName;
        this.weight = weight;
        this.departureAddress = departureAddress;
        this.destinationAddress = destinationAddress;
        this.routeLength = routeLength;
        this.departureDate = departureDate;
        this.destinationDate = destinationDate;
        this.orderPrice = orderPrice;
        this.transportId = transportId;
    }

    public int getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCargoName() {
        return cargoName;
    }

    public int getWeight() {
        return weight;
    }

    public String getDepartureAddress() {
        return departureAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public int getRouteLength() {
        return routeLength;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getDestinationDate() {
        return destinationDate;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public String getStatus() {
        return status;
    }

    public int getTransportId() {
        return transportId;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDepartureAddress(String departureAddress) {
        this.departureAddress = departureAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public void setRouteLength(int routeLength) {
        this.routeLength = routeLength;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setDestinationDate(LocalDate destinationDate) {
        this.destinationDate = destinationDate;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", cargoName='" + cargoName + '\'' +
                ", weight=" + weight +
                ", departureAddress='" + departureAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", routeLength=" + routeLength +
                ", departureDate=" + departureDate +
                ", destinationDate=" + destinationDate +
                ", orderPrice=" + orderPrice +
                ", status='" + status + '\'' +
                ", transportId=" + transportId +
                '}';
    }
}

package com.CRMLogistic.orderservice.Service;

import com.CRMLogistic.orderservice.Model.Order;

import java.util.List;

public interface OrderService {

    void create(Order order);

    void delete(int id);

    Order getOrder(int id);
    void edit(Order order);

    List<Order> all();
}

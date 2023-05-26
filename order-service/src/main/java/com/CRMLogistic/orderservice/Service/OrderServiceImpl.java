package com.CRMLogistic.orderservice.Service;

import com.CRMLogistic.orderservice.Model.Order;
import com.CRMLogistic.orderservice.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void create(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order getOrder(int id) {
       return orderRepository.getReferenceById(id);
    }

    @Override
    public void edit(Order order) {
    Order modifiedOrder = orderRepository.getReferenceById(order.getId());
        modifiedOrder.setOrderNumber(order.getOrderNumber());
        modifiedOrder.setCargoName(order.getCargoName());
        modifiedOrder.setWeight(order.getWeight());
        modifiedOrder.setDepartureAddress(order.getDepartureAddress());
        modifiedOrder.setDestinationAddress(order.getDestinationAddress());
        modifiedOrder.setRouteLength(order.getRouteLength());
        modifiedOrder.setDepartureDate(order.getDepartureDate());
        modifiedOrder.setDestinationDate(order.getDestinationDate());
        modifiedOrder.setOrderPrice(order.getOrderPrice());
        modifiedOrder.setStatus(order.getStatus());
        modifiedOrder.setTransportId(order.getTransportId());
    orderRepository.save(modifiedOrder);
    }

    @Override
    public List<Order> all() {
        return orderRepository.findAll();
    }

    @Override
    public void changeStatus(int id,String newStatus) {
        Order order = orderRepository.getReferenceById(id);
        order.setStatus(newStatus);
        orderRepository.save(order);
    }

    @Override
    public List<Order> todayOrders() {
        return orderRepository.selectTodayOrders();
    }

    @Override
    public List<Order> tomorrowOrders() {
        return orderRepository.selectTomorrowOrders();
    }

    @Override
    public List<Order> otherDaysOrders() {
        return orderRepository.selectOtherDays();
    }

    @Override
    public List<Order> orderStatusOf(String status) {
        return orderRepository.findByStatus(status);
    }
}

package com.CRMLogistic.orderservice.Controller;

import com.CRMLogistic.orderservice.Model.Order;
import com.CRMLogistic.orderservice.Model.Response;
import com.CRMLogistic.orderservice.Service.OrderProducer;
import com.CRMLogistic.orderservice.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/")
public class OrderController {

     DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProducer orderProducer;

    @PostMapping("orders")
    public void createNewOrder(@PathVariable Order order){
        orderService.create(order);
        orderProducer.sendMessage(LocalDateTime.now().format(pattern) + " CREATED NEW ORDER "+ order);

    }
    @GetMapping("orders/{id}")
    public Order get(@PathVariable int id){
        return orderService.getOrder(id);
    }
    @PutMapping("orders")
    public void editOrder(@PathVariable Order order){
        orderService.edit(order);
        orderProducer.sendMessage(LocalDateTime.now().format(pattern) + " THE ORDER NUMBER "+
                order.getOrderNumber()+" HAS BEEN EDITED: " + order);
    }
    @DeleteMapping("orders/{id}")
    public void deleteOrder(@PathVariable int id){
        Order order = orderService.getOrder(id);
        orderProducer.sendMessage(LocalDateTime.now().format(pattern) + " ORDER NUMBER " +order.getOrderNumber() +
                " HAS BEEN DELETED:" +order);
        orderService.delete(id);
    }
    @GetMapping("orders/all")
    public List<Order> showAllOrder(){
        return orderService.all();
    }

    @GetMapping("orders/directionsApi/{startLocation}/{endLocation}")
    public Long directionsApi(@PathVariable String startLocation, @PathVariable String endLocation) {
        String startAddress = startLocation.replaceAll(" ", "+");
        String finishAddress = endLocation.replaceAll(" ", "+");
        String key = "AIzaSyC9Txy-V6PZTtv2fQRxcYBBW0eESWP5Rfk";
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" +
                startAddress + "&destination=" + finishAddress + "&key=" + key;
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.getForObject(url, Response.class);
        return response.getRoutes()[0].getLegs()[0].getDistance().getValue();
    }
    @PutMapping("orders/status/change/{id}&{newStatus}")
    public void changeOrderStatus(@PathVariable int id, @PathVariable String newStatus) {
        Order order = orderService.getOrder(id);
        orderService.changeStatus(id,newStatus);
        orderProducer.sendMessage(LocalDateTime.now().format(pattern) +" ORDER STATUS "
                + order.getOrderNumber()+ " CHANGED TO: " + newStatus);
    }

    @GetMapping("orders/select/today")
    public List<Order> showTodayOrders(){
        return orderService.todayOrders();
    }

    @GetMapping("orders/select/tomorrow")
    public List<Order> showTomorrowOrders(){
        return orderService.tomorrowOrders();
    }
    @GetMapping("orders/select/otherDays")
    public List<Order> showOtherDaysOrders(){
        return orderService.otherDaysOrders();
    }

    @GetMapping("orders/status/{statusOf}")
    public List<Order> showOrderStatusOf(@PathVariable String statusOf){
        return orderService.orderStatusOf(statusOf);
    }
}

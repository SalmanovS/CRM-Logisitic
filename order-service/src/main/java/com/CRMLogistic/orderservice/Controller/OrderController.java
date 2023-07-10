package com.CRMLogistic.orderservice.Controller;

import com.CRMLogistic.orderservice.Model.Order;
import com.CRMLogistic.orderservice.Model.DistanceResponse;
import com.CRMLogistic.orderservice.Service.OrderProducer;
import com.CRMLogistic.orderservice.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


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
    public int directionsApi(@PathVariable String startLocation, @PathVariable String endLocation) {
        String key = "OmCQG51EuRFDlkZZvKyCn8J2OrfDlZYe";
        String url = "https://www.mapquestapi.com/directions/v2/route?key="+key+"&from="+startLocation+"&to="+endLocation;
        DistanceResponse distance = WebClient.create()
                .get().
                uri(url)
                .retrieve()
                .bodyToMono(DistanceResponse.class)
                .block();
        int convertDistance = (int) Math.ceil(distance.getRoute().getDistance() * 1.61);
        orderProducer.sendMessage(LocalDateTime.now().format(pattern) +" The length of the route from "+startLocation
                + " to "+endLocation+ " = "+convertDistance);
            return convertDistance;





    }
    @PutMapping("orders/status/change/{id}&{newStatus}")
    public void changeOrderStatus(@PathVariable int id, @PathVariable String newStatus) {
        Order order = orderService.getOrder(id);
        orderService.changeStatus(id,newStatus);
        //check which status has been transferred and change the status of the transport
        if(newStatus.equals("In progress")){
            WebClient.create().put().uri("http://localhost:8083/api/transports/changeStatus/{id}/{newStatus}",order.getTransportId()
            ,"In work").retrieve().bodyToMono(Void.class).block();
        }if(newStatus.equals("Completed"))
            WebClient.create().put().uri("http://localhost:8083/api/transports/changeStatus/{id}/{newStatus}",order.getTransportId()
                    ,"Free").retrieve().bodyToMono(Void.class).block();
        // --------------------------------------------------
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

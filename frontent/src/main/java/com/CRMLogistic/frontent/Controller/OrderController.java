package com.CRMLogistic.frontent.Controller;


import com.CRMLogistic.frontent.Model.Order;
import com.CRMLogistic.frontent.Model.Transport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/")
    public String index(Model model){
        List<Order> todayOrders = WebClient.create().get().uri("http://localhost:8081/api/orders/select/today")
                .retrieve().bodyToFlux(Order.class).collectList().block();
        List<Order> tomorrowOrders = WebClient.create().get().uri("http://localhost:8081/api/orders/select/tomorrow")
                        .retrieve().bodyToFlux(Order.class).collectList().block();
        List<Order> otherDaysOrders = WebClient.create().get().uri("http://localhost:8081/api/orders/select/otherDays")
                        .retrieve().bodyToFlux(Order.class).collectList().block();
        List<Order> orderStatusInProgress = WebClient.create().get().uri("http://localhost:8081/api/orders/status/{statusOf}"
                , "In progress" ).retrieve().bodyToFlux(Order.class).collectList().block();
        List<Order> orderStatusScheduled = WebClient.create().get().uri("http://localhost:8081/api/orders/status/{statusOf}"
        , "Scheduled").retrieve().bodyToFlux(Order.class).collectList().block();
        List<Order> orderStatusCompleted = WebClient.create().get().uri("http://localhost:8081/api/orders/status/{statusOf}"
        ,"Completed").retrieve().bodyToFlux(Order.class).collectList().block();
        model.addAttribute("todayOrders",todayOrders);
        model.addAttribute("tomorrowOrders",tomorrowOrders);
        model.addAttribute("otherDaysOrders",otherDaysOrders);
        model.addAttribute("orderStatusInProgress",orderStatusInProgress);
        model.addAttribute("orderStatusScheduled",orderStatusScheduled);
        model.addAttribute("orderStatusCompleted",orderStatusCompleted);
        return "order/index";
    }

    @GetMapping("status/{id}/{status}")
    @ResponseBody
    public void changeOrderStatus(@PathVariable int id, @PathVariable String status){
        WebClient.create().put().uri("http://localhost:8081/api/orders/status/change/{id}&{newStatus}",
                id,status).retrieve().bodyToMono(Void.class).block();

    }
    @RequestMapping("/info/{id}")
    public String getInfo(@PathVariable int id, Model model){
        Order order = WebClient.create().get().uri("http://localhost:8081/api/orders/{id}",id)
                .retrieve().bodyToMono(Order.class).block();
        Transport transport = WebClient.create().get().uri("http://localhost:8083/api/transports/{id}"
                ,order.getTransportId()).retrieve().bodyToMono(Transport.class).block();
        model.addAttribute("order",order);
        model.addAttribute("transport",transport);
        return "order/info";
    }
    @RequestMapping("/create")
    public String create(Model model){
        // generation order number
        LocalDateTime localDateTime = LocalDateTime.now();
        String orderNumber = localDateTime.getYear() +"" + localDateTime.getMonthValue()+ ""+localDateTime.getDayOfMonth() +
                ""+ localDateTime.getHour()+""+ localDateTime.getMinute()+""+ localDateTime.getSecond();
        //--------------------------
        List<Transport> transportList = WebClient.create().get().uri("http://localhost:8083/api/transports/all")
                .retrieve().bodyToFlux(Transport.class).collectList().block();
        model.addAttribute("orderNumber",orderNumber);
        model.addAttribute("transportList",transportList);
        return "order/create";
    }

    @RequestMapping("/directions")
    @ResponseBody
    @SuppressWarnings("ConstantConditions")
    public int distance(@RequestParam("startLocation") String startLocation, @RequestParam("endLocation") String endLocation){
      RestTemplate restTemplate = new RestTemplate();
     int distance = WebClient.create().get().uri("http://localhost:8081/api/orders/directionsApi/{startLocation}/{endLocation}",
              startLocation,endLocation).retrieve().bodyToMono(Integer.class).block();

       return distance;

    }
}

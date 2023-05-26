package com.CRMLogistic.frontent.Controller;


import com.CRMLogistic.frontent.Model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

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
                , "InProgress" ).retrieve().bodyToFlux(Order.class).collectList().block();
        model.addAttribute("todayOrders",todayOrders);
        model.addAttribute("tomorrowOrders",tomorrowOrders);
        model.addAttribute("otherDaysOrders",otherDaysOrders);
        model.addAttribute("orderStatusInProgress",orderStatusInProgress);
        return "order/index";
    }

    @GetMapping("status/{id}/{status}")
    @ResponseBody
    public void changeOrderStatus(@PathVariable int id, @PathVariable String status){
        WebClient.create().put().uri("http://localhost:8081/api/orders/status/change/{id}&{newStatus}",
                id,status).retrieve().bodyToMono(Void.class).block();
    }
}

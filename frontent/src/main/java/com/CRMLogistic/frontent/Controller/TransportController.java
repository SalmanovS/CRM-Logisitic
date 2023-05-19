package com.CRMLogistic.frontent.Controller;

import com.CRMLogistic.frontent.Model.Transport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping("/transport")
public class TransportController {

    @RequestMapping("/")
    public String home(Model model){
        List<Transport> allTransport = WebClient.create().get().uri("http://localhost:8083/api/transports/all")
                .retrieve().bodyToFlux(Transport.class).collectList().block();
        model.addAttribute("allTransport",allTransport);
        return "transport/index";
    }
    @RequestMapping("/get/{id}")
    public String get(@PathVariable int id, Model model){
        Transport showDetailTransport = WebClient.create().get().uri("http://localhost:8083/api/transports/{id}", id)
                .retrieve().bodyToMono(Transport.class).block();
        model.addAttribute("showDetailTransport",showDetailTransport);
        return "transport/info";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        WebClient.create().delete().uri("http://localhost:8083/api/transports/{id}", id).retrieve()
                .bodyToMono(Void.class).block();
        return "redirect:/transport/";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        Transport transport = WebClient.create().get().uri("http://localhost:8083/api/transports/{id}", id)
                .retrieve().bodyToMono(Transport.class).block();
        model.addAttribute("transport",transport);
        return "transport/edit";
    }
    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id, @ModelAttribute Transport modifiedTransport){
        Transport transport = WebClient.create().get().uri("http://localhost:8083/api/transports/{id}", id)
                .retrieve().bodyToMono(Transport.class).block();
        transport.setBrand(modifiedTransport.getBrand());
        transport.setModel(modifiedTransport.getModel());
        transport.setNumber(modifiedTransport.getNumber());
        transport.setBodyType(modifiedTransport.getBodyType());
        transport.setCarryingCapacity(modifiedTransport.getCarryingCapacity());
        transport.setStatus(modifiedTransport.getStatus());
        WebClient.create().put().uri("http://localhost:8083/api/transports").body(Mono.just(transport),
                        Transport.class).retrieve().bodyToMono(Void.class).block();
        return "redirect:/transport/";

    }
    @RequestMapping("create")
    public String create(){
        return "transport/create";
    }
    @RequestMapping("add")
    public String add(@ModelAttribute Transport newTransport){
        WebClient.create().post().uri("http://localhost:8083/api/transports")
                .body(Mono.just(newTransport),Transport.class).retrieve().bodyToMono(Void.class).block();

        return "redirect:/transport/";
    }



}

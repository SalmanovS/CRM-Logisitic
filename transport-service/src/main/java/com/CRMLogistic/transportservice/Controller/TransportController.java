package com.CRMLogistic.transportservice.Controller;

import com.CRMLogistic.transportservice.Model.Transport;
import com.CRMLogistic.transportservice.Service.TransportProducer;
import com.CRMLogistic.transportservice.Service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api")
public class TransportController {
    private final String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    @Autowired
    TransportService transportService;
    @Autowired
    TransportProducer  transportProducer;

    @PostMapping("/transports")
    public void createNewTransport(@RequestBody Transport transport){
        transportService.create(transport);
        transportProducer.sendMessage(localDateTime + " Create new transport " + transport.toString());
    }
    @GetMapping("transports/{id}")
    public Transport getTransport(@PathVariable int id){
        return transportService.getTransport(id);
    }
    @DeleteMapping("transports/{id}")
    public void deleteTransport(@PathVariable int id){
        transportProducer.sendMessage(localDateTime+ " Transport = " + transportService.getTransport(id).toString() +
                " is delete.");
        transportService.delete(id);

    }
    @PutMapping("/transports")
    public void editTransport(@RequestBody Transport modifiedTransport){
        Transport transport = getTransport(modifiedTransport.getId());
        transportService.edit(modifiedTransport);
        transportProducer.sendMessage(localDateTime + "Object " +transport.toString()+ " was changed to " +
                modifiedTransport.toString());
    }

    @GetMapping("/transports/all")
    public List<Transport> allTransport(){
        return transportService.all();
    }

    @GetMapping("/transports/SearchByStatus/{status}")
    public List<Transport> listOfTransportWithStatus(@PathVariable String status){
        return transportService.showTransportWithStatus(status);
    }

    @PutMapping("/transports/changeStatus/{id}/{newStatus}")
    public void changeTransportStatus(@PathVariable int id, @PathVariable String newStatus){
        Transport transport = transportService.getTransport(id);
        transportService.changeStatus(id,newStatus);
        transportProducer.sendMessage(localDateTime + " The object  " + transport.toString() +
                " has changed its status to " + newStatus);
    }
}

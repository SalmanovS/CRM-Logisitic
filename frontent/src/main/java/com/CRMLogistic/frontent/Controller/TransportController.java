package com.CRMLogistic.frontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transport")
public class TransportController {

    @RequestMapping("/")
    public String home(){
        return "transport/index";
    }

}

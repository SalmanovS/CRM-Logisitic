package com.CRMLogistic.frontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping("/")
    public String homePageIndex(){
        return "home/home-page";
    }
}

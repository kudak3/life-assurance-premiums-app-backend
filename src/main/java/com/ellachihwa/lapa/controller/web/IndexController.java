package com.ellachihwa.lapa.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {


    public String viewHomePage(Model model) {
        return "index";
    }

    @GetMapping("/config")
    public String configurationPage(){
        return "admin/configurations";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}

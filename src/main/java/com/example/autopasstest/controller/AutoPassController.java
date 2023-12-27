package com.example.autopasstest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoPassController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}

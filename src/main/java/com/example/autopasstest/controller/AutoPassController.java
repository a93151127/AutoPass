package com.example.autopasstest.controller;

import com.example.autopasstest.autopass.AccessTokenRes;
import com.example.autopasstest.autopass.AutoPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoPassController {

    @Autowired
    private AutoPassService autoPassService;

    @RequestMapping("/hello")
    public String hello(){

        AccessTokenRes res = autoPassService.doAccessToken();
        String accessToken = res.getAccessToken();
        System.out.println(accessToken);

        return res.toString();
    }
}

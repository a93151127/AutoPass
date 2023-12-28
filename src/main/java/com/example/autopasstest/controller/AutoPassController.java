package com.example.autopasstest.controller;

import com.example.autopasstest.autopass.AccessTokenRes;
import com.example.autopasstest.autopass.AutoPassService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoPassController {

    @Autowired
    private AutoPassService autoPassService;

    @RequestMapping("/accessToken")
    public AccessTokenRes accessToken() throws JsonProcessingException {
        AccessTokenRes res = autoPassService.doAccessToken();

        return res;
    }
}

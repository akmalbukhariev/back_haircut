package com.barbie.haircut.api.hairdresser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainForms {

    @GetMapping("/hello")
    public String getHello(){
        return "Hello";
    }
}

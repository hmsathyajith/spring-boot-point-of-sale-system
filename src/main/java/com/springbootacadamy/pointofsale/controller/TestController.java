package com.springbootacadamy.pointofsale.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/test")
public class TestController {

    @GetMapping(path="/text")
    public String getMyText(){
        String text = "this is my first spring boot app";
        System.out.println(text);
        return text;
    }

    @GetMapping(path = "/text2")
    public String getMyText1(){
        String text = "this is my second spring boot app";
        System.out.println(text);
        return text;
    }
}

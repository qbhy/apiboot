package com.qbhy.apiboot.http.controllers;

import com.qbhy.apiboot.exceptions.ExampleException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    String hello() {
        return "Hello World!";
    }

    @RequestMapping("/exception")
    String testException() throws ExampleException {
        throw new ExampleException("testException");
    }

}

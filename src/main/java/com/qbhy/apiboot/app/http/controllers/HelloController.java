package com.qbhy.apiboot.app.http.controllers;

import com.qbhy.apiboot.app.exceptions.ExampleException;
import com.qbhy.apiboot.app.http.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController extends Controller {

    @RequestMapping("/")
    Response hello() {
        return ok("hello");
    }

    @RequestMapping("/exception")
    String testException() throws ExampleException {
        throw new ExampleException("testException");
    }

}

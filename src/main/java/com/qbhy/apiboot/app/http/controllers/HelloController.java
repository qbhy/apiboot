package com.qbhy.apiboot.app.http.controllers;

import com.qbhy.apiboot.app.exceptions.ExampleException;
import com.qbhy.apiboot.app.http.response.Response;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CacheConfig(cacheNames = "hello")
public class HelloController extends Controller {

    @RequestMapping("/")
    @Cacheable
    public Response hello() {
        System.out.println("hello 方法被调用了");
        return ok("hello");
    }

    @RequestMapping("/exception")
    String testException() throws ExampleException {
        throw new ExampleException("testException");
    }


}

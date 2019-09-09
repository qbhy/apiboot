package com.qbhy.apiboot.app.http.controllers;

import com.qbhy.apiboot.app.exceptions.ExampleException;
import com.qbhy.apiboot.framework.http.response.Response;
import com.qbhy.apiboot.framework.http.Controller;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CacheConfig(cacheNames = "hello")
public class HelloController extends Controller {

//    @Cacheable
    @RequestMapping("/")
    public Response hello() {
        System.out.println("hello 方法被调用了");
        return ok("hello");
    }

    @RequestMapping("/exception")
    String testException() throws ExampleException {
        throw new ExampleException("testException");
    }


}

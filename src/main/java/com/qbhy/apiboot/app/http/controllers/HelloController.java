package com.qbhy.apiboot.app.http.controllers;

import com.qbhy.apiboot.app.exceptions.ExampleException;
import com.qbhy.apiboot.framework.http.middleware.Middleware;
import com.qbhy.apiboot.framework.http.controller.BaseController;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CacheConfig(cacheNames = "hello")
@Middleware(groups = "example")
public class HelloController extends BaseController {

    @RequestMapping("/")
    public Object hello() {
        return ok("hello controller");
    }

    @RequestMapping("/exception")
   public Object testException() throws Throwable {
        throw new ExampleException("testException");
    }


}

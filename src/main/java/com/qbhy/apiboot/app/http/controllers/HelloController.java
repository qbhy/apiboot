package com.qbhy.apiboot.app.http.controllers;

import com.qbhy.apiboot.app.exceptions.ExampleException;
import com.qbhy.apiboot.framework.http.Middleware;
import com.qbhy.apiboot.framework.http.response.Response;
import com.qbhy.apiboot.framework.http.BaseController;
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
    Object testException() throws Throwable {
        throw new ExampleException("testException");
    }


}

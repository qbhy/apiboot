package com.qbhy.apiboot.app.http.controllers;

import com.qbhy.apiboot.app.exceptions.ExampleException;
import com.qbhy.apiboot.app.http.middlewares.ExampleMiddleware;
import com.qbhy.apiboot.framework.http.Middleware;
import com.qbhy.apiboot.framework.http.response.Response;
import com.qbhy.apiboot.framework.http.Controller;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CacheConfig(cacheNames = "hello")
@Middleware(group = "example", excludeGlobal = true)
public class HelloController extends Controller {

    //    @Cacheable
    @RequestMapping("/")
//    @Middleware(group = "example")
    public Response hello() {
        return ok("hello controller");
    }

    @RequestMapping("/exception")
    String testException() throws Throwable {
        throw new ExampleException("testException");
    }


}

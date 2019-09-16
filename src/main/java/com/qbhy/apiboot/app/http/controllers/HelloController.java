package com.qbhy.apiboot.app.http.controllers;

import com.qbhy.apiboot.app.exceptions.ExampleException;
import com.qbhy.apiboot.framework.auth.AuthManager;
import com.qbhy.apiboot.framework.http.Middleware;
import com.qbhy.apiboot.framework.http.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
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

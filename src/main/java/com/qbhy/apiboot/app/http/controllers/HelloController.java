package com.qbhy.apiboot.app.http.controllers;

import com.qbhy.apiboot.app.exceptions.ExampleException;
import com.qbhy.apiboot.app.models.User;
import com.qbhy.apiboot.framework.auth.AuthManager;
import com.qbhy.apiboot.framework.http.middlewares.Middleware;
import com.qbhy.apiboot.framework.http.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CacheConfig(cacheNames = "hello")
@Middleware(groups = "example")
public class HelloController extends BaseController {

    @Autowired
    AuthManager authManager;

    @RequestMapping("/")
    public Object hello() {
        return ok("hello controller");
    }

    @RequestMapping("/auth")
    @Middleware(groups = "jwt.auth")
    public Object auth(HttpServletRequest request) throws Throwable {
        User user = (User) authManager.user(request);
        return ok(user);
    }

    @RequestMapping("/exception")
    public Object testException() throws Throwable {
        throw new ExampleException("testException");
    }
}

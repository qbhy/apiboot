package com.qbhy.apiboot.app.http.controllers;

import com.qbhy.apiboot.app.exceptions.ExampleException;
import com.qbhy.apiboot.app.models.User;
import com.qbhy.apiboot.framework.auth.AuthManager;
import com.qbhy.apiboot.framework.http.middlewares.Middleware;
import com.qbhy.apiboot.framework.http.controller.BaseController;
import org.apache.catalina.connector.RequestFacade;
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
    public Object hello(RequestFacade request) {
        User user = (User) authManager.user();
//        User user = (User) authManager.user("guard");
        return ok(user != null ? user.getName() + "用户已经登录" : "用户未登录");
    }

    @RequestMapping("/auth")
    @Middleware(groups = "jwt.auth")
    public Object auth(HttpServletRequest request) {
        return ok(authManager.user());
    }

    @RequestMapping("/exception")
    public Object testException() throws Throwable {
        throw new ExampleException("testException");
    }
}

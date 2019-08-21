package com.qbhy.apiboot.app.http.middlewares;

import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class ExampleMiddleware extends Middleware {
    @Override
    void handle(HttpServletRequest request, ServletResponse response, FilterChain chain) throws Exception {
        System.out.println("handle middleware");
    }
}

package com.qbhy.apiboot.app.http.middlewares;

import com.qbhy.apiboot.framework.http.Middleware;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class ExampleMiddleware extends Middleware {
    @Override
    public void handle(HttpServletRequest request, ServletResponse response, FilterChain chain) throws Exception {
        System.out.println("执行中间件");
    }
}

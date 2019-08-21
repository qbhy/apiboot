package com.qbhy.apiboot.app.http.middlewares;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

abstract public class Middleware implements Filter {

    abstract void handle(HttpServletRequest request, ServletResponse response, FilterChain chain) throws Exception;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            try {
                this.handle(request, response, chain);
            } catch (Exception e) {
                return;
            }
        }
        chain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}

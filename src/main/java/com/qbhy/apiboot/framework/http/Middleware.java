package com.qbhy.apiboot.framework.http;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

abstract public class Middleware implements Filter {

    public abstract void handle(HttpServletRequest request, ServletResponse response, FilterChain chain) throws Exception;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            try {
                this.handle(request, response, chain);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        chain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}

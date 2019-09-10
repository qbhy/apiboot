package com.qbhy.apiboot.app.http.middlewares;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;
import com.qbhy.apiboot.framework.http.HttpMiddleware;

import javax.servlet.http.HttpServletRequest;

public class ExampleMiddleware extends HttpMiddleware {
    @Override
    public Object handle(HttpServletRequest request, Stack stack) throws Throwable {
        System.out.println("ExampleMiddleware");
        return stack.next(request);
    }
}

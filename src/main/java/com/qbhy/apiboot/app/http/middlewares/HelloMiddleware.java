package com.qbhy.apiboot.app.http.middlewares;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;
import com.qbhy.apiboot.framework.http.HttpMiddleware;
import com.qbhy.apiboot.framework.http.response.Response;

import javax.servlet.http.HttpServletRequest;

public class HelloMiddleware extends HttpMiddleware {
    @Override
    public Object handle(HttpServletRequest request, Stack stack) throws Throwable {
        return stack.next(request);
    }
}

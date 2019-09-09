package com.qbhy.apiboot.app.http.middlewares;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;
import com.qbhy.apiboot.framework.http.HttpMiddleware;
import com.qbhy.apiboot.framework.http.response.Response;
import org.springframework.http.server.ServletServerHttpRequest;

public class HelloMiddleware extends HttpMiddleware {
    @Override
    public Response handle(ServletServerHttpRequest request, Stack stack) throws Throwable {
        Object result = stack.next(request);
        return (Response) result;
    }
}

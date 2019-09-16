package com.qbhy.apiboot.app.http.middlewares;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;
import com.qbhy.apiboot.framework.http.HttpMiddleware;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class ExampleMiddleware extends HttpMiddleware {
    @Override
    public ResponseEntity handle(HttpServletRequest request, Stack stack) throws Throwable {
        ResponseEntity response = (ResponseEntity) stack.next(request);
        response.getHeaders().set("test", "example");
        return  response;
    }
}

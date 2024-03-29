package com.qbhy.apiboot.app.http.middlewares;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;
import com.qbhy.apiboot.framework.http.middlewares.HttpMiddleware;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class ExampleGlobalMiddleware extends HttpMiddleware {
    @Override
    public ResponseEntity handle(HttpServletRequest request, Stack stack) throws Throwable {
        return (ResponseEntity) stack.next(request);
    }
}

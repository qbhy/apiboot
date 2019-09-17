package com.qbhy.apiboot.framework.http.middlewares;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Dockable;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

abstract public class HttpMiddleware implements Dockable {
    @Override
    public Object handle(Object passable, Stack stack) throws Throwable {
        if (passable instanceof HttpServletRequest) {
            return handle((HttpServletRequest) passable, stack);
        }
        return stack.next(passable);
    }

    abstract public ResponseEntity handle(HttpServletRequest request, Stack stack) throws Throwable;
}

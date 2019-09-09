package com.qbhy.apiboot.framework.http;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Dockable;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;
import com.qbhy.apiboot.framework.http.response.Response;
import org.springframework.http.server.ServletServerHttpRequest;

abstract public class HttpMiddleware implements Dockable {
    @Override
    public Object handle(Object passable, Stack stack) throws Throwable {
        if (passable instanceof ServletServerHttpRequest) {
            return handle((ServletServerHttpRequest) passable, stack);
        }
        return stack.next(passable);
    }

    abstract public Response handle(ServletServerHttpRequest request, Stack stack) throws Throwable;
}

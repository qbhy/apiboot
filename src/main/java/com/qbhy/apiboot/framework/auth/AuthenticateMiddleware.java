package com.qbhy.apiboot.framework.auth;

import com.qbhy.apiboot.framework.contracts.auth.Guard;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;
import com.qbhy.apiboot.framework.foundation.App;
import com.qbhy.apiboot.framework.http.middlewares.HttpMiddleware;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class AuthenticateMiddleware extends HttpMiddleware {

    private String guardName;

    public AuthenticateMiddleware(String guard) {
        guardName = guard;
    }

    @Override
    public ResponseEntity handle(HttpServletRequest request, Stack stack) throws Throwable {

        AuthManager authManager = App.getBean(AuthManager.class);
        Guard guard = authManager.guard(guardName);

        if (guard.parseCredentials(request).guest()) {
            throw new UnauthorizedException("未经授权的请求!");
        }

        authManager.setGuard(request, guard);

        return (ResponseEntity) stack.next(request);
    }
}

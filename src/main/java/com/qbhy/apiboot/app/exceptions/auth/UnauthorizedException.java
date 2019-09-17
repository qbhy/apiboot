package com.qbhy.apiboot.app.exceptions.auth;

import com.qbhy.apiboot.framework.debug.RenderableException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends RenderableException {

    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}

package com.qbhy.apiboot.framework.auth;

import com.qbhy.apiboot.framework.debug.RenderableException;
import org.springframework.http.HttpStatus;

public class MissingCredentialException extends RenderableException {
    public MissingCredentialException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}

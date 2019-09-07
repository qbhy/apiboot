package com.qbhy.apiboot.app.exceptions;

import com.qbhy.apiboot.framework.debug.RenderableException;
import org.springframework.http.HttpStatus;

public class ExampleException extends RenderableException {

    public ExampleException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}

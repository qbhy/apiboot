package com.qbhy.apiboot.app.exceptions;

import org.springframework.http.HttpStatus;

public class ExampleException extends RenderableException {

    public ExampleException(String message) {
        super();
    }

    @Override
    HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}

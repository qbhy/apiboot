package com.qbhy.apiboot.exceptions;

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

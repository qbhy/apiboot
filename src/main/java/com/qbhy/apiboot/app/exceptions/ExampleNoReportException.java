package com.qbhy.apiboot.app.exceptions;

import com.qbhy.apiboot.framework.debug.RenderableException;
import org.springframework.http.HttpStatus;

public class ExampleNoReportException extends RenderableException {

    public ExampleNoReportException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}

package com.qbhy.apiboot.framework.contracts.debug;

import com.qbhy.apiboot.framework.http.response.Response;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface HttpExceptionHandler {
    public ResponseEntity<?> handle(HttpServletRequest request, Throwable throwable);

    public void report(Throwable throwable);
}

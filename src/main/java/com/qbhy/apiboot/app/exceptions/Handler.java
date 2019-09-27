package com.qbhy.apiboot.app.exceptions;

import com.qbhy.apiboot.framework.debug.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Component
public class Handler extends ExceptionHandler {

    protected List<Class<? extends Throwable>> dontReports = Arrays.asList(
            ExampleException.class,
            ExampleNoReportException.class
    );

    @Override
    public void report(Throwable throwable) {
        if (shouldReport(throwable)) {
            // 在这里写你的通知代码
        }
    }

    @Override
    public ResponseEntity<?> handle(HttpServletRequest request, Throwable throwable) {
        return super.handle(request, throwable);
    }
}

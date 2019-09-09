package com.qbhy.apiboot.app.exceptions;

import com.qbhy.apiboot.framework.contracts.debug.HttpExceptionHandler;
import com.qbhy.apiboot.framework.debug.RenderableException;
import com.qbhy.apiboot.framework.http.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler implements HttpExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(Handler.class);

    public void report(Throwable throwable) {
        throwable.printStackTrace();
        logger.debug("exception handler", throwable);
    }

    /**
     * @param request   请求实例
     * @param throwable 异常实例
     * @return 响应实例
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<?> handle(HttpServletRequest request, Throwable throwable) {
        this.report(throwable);

        HttpStatus status = getStatus(request, throwable);
        Object data = reformat(throwable);

        if (throwable instanceof RenderableException) {
            RenderableException e = (RenderableException) throwable;
            e.setData(data);
            return e.render(request, status).responseEntity();
        }

        return Response.fail(status, throwable.getMessage(), data).responseEntity();
    }

    /**
     * @param request   请求实例
     * @param throwable 异常实例
     * @return 返回标准 http 状态码
     */
    private HttpStatus getStatus(HttpServletRequest request, Throwable throwable) {
        if (throwable instanceof RenderableException) {
            return ((RenderableException) throwable).getHttpStatus();
        }
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    public static HashMap<String, Object> reformat(Throwable throwable) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("class", throwable.getClass().getName());
        map.put("message", throwable.getMessage());
        map.put("trace", throwable.getStackTrace());
        return map;
    }
}

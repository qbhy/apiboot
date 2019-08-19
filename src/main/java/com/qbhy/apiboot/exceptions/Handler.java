package com.qbhy.apiboot.exceptions;

import com.qbhy.apiboot.http.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {

    /**
     * @param request 请求实例
     * @param throwable 异常实例
     * @return 响应实例
     */
    @ExceptionHandler(RenderableException.class)
    @ResponseBody
    ResponseEntity<?> handle(HttpServletRequest request, Throwable throwable) {
        HttpStatus status = getStatus(request);

        if (throwable instanceof RenderableException) {
            return new ResponseEntity<>(((RenderableException) throwable).render(request, status), status);
        }

        return new ResponseEntity<>(new Response(status, throwable.getMessage()), status);
    }

    /**
     * @param request 请求实例
     * @return 返回 http 标准状态码
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}

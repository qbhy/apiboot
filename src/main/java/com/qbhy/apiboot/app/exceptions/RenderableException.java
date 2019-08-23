package com.qbhy.apiboot.app.exceptions;

import com.qbhy.apiboot.app.http.response.Response;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

abstract public class RenderableException extends Exception {

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    Response render(HttpServletRequest request, HttpStatus httpStatus) {
        return Response.fail(httpStatus, this.getMessage(), data);
    }

    /**
     * @return http 标准状态码
     */
    abstract HttpStatus getHttpStatus();


}

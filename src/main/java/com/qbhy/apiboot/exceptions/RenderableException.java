package com.qbhy.apiboot.exceptions;

import com.qbhy.apiboot.http.response.Response;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

abstract class RenderableException extends Exception {

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    Response render(HttpServletRequest request, HttpStatus httpStatus) {
        return new Response(httpStatus, this.getMessage(), data);
    }

    /**
     * @return http 标准状态码
     */
    abstract HttpStatus getHttpStatus();


}

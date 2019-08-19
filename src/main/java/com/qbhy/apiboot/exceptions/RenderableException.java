package com.qbhy.apiboot.exceptions;

import com.qbhy.apiboot.http.response.Response;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

abstract public class RenderableException extends Exception {

    public Response render(HttpServletRequest request, HttpStatus httpStatus) {
        return new Response(httpStatus, "测试");
    }
}

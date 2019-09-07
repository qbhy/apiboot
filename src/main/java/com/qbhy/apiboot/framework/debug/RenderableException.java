package com.qbhy.apiboot.framework.debug;

import com.qbhy.apiboot.framework.http.response.Response;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public abstract class RenderableException extends Exception {

    public RenderableException(String message) {
        super(message);
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    public Response render(HttpServletRequest request, HttpStatus httpStatus) {
        return Response.fail(httpStatus, this.getMessage(), data);
    }

    /**
     * @return http 标准状态码
     */
    public abstract HttpStatus getHttpStatus();


}

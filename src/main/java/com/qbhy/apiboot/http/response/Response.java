package com.qbhy.apiboot.http.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {
    private int code;
    private HttpStatus statusCode;
    private String message;
    private Object data;

    public Response(HttpStatus statusCode, int code, String message, Object data) {
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public Response(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.code = 0;
        this.data = null;
    }

    public Response(HttpStatus statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.code = 0;
    }

    public Response(String message, Object data, int code) {
        this.statusCode = HttpStatus.BAD_REQUEST;
        this.message = message;
        this.data = data;
        this.code = code;
    }
}

package com.qbhy.apiboot.http.response;

import lombok.Data;

@Data
public class Response {
    private int code;
    private int statusCode;
    private String message;
    private Object data;

    public Response(int statusCode, int code, String message, Object data) {
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public Response(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.code = 0;
        this.data = null;
    }

    public Response(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.code = 0;
    }

    public Response(String message, Object data, int code) {
        this.statusCode = 400;
        this.message = message;
        this.data = data;
        this.code = code;
    }
}

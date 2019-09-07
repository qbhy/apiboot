package com.qbhy.apiboot.framework.http.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Data
public class Response implements Serializable {
    private Integer code;
    private HttpStatus statusCode;
    private String message;
    private Object data;

    public static Response ok(Object data) {
        return new Response(HttpStatus.OK, 0, "ok", data);
    }

    public Response(HttpStatus statusCode, Integer code, String message, Object data) {
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static Response fail(Object data) {
        return new Response(HttpStatus.BAD_REQUEST, -1, "fail", data);
    }

    public static Response fail(HttpStatus statusCode, String msg) {
        return new Response(statusCode, -1, msg, null);
    }

    public static Response fail(HttpStatus statusCode, String msg, Object data) {
        return new Response(statusCode, -1, msg, data);
    }

    public ResponseEntity<?> responseEntity(){
        return new ResponseEntity<>(this, this.statusCode);
    }
}

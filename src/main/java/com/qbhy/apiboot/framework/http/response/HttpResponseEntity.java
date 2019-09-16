package com.qbhy.apiboot.framework.http.response;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;

public class HttpResponseEntity<T> extends ResponseEntity<T> {

    private HttpHeaders httpHeaders = new HttpHeaders();

    /**
     * Create a new {@code ResponseEntity} with the given status code, and no body nor headers.
     *
     * @param status the status code
     */
    public HttpResponseEntity(HttpStatus status) {
        super(status);
    }

    /**
     * Create a new {@code ResponseEntity} with the given body and status code, and no headers.
     *
     * @param body   the entity body
     * @param status the status code
     */
    public HttpResponseEntity(T body, HttpStatus status) {
        super(body, status);
    }

    /**
     * Create a new {@code HttpEntity} with the given headers and status code, and no body.
     *
     * @param headers the entity headers
     * @param status  the status code
     */
    public HttpResponseEntity(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
        httpHeaders.putAll(headers);
    }

    /**
     * Create a new {@code HttpEntity} with the given body, headers, and status code.
     *
     * @param body    the entity body
     * @param headers the entity headers
     * @param status  the status code
     */
    public HttpResponseEntity(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
        httpHeaders.putAll(headers);
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.httpHeaders;
    }
}

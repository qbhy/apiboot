package com.qbhy.apiboot.framework.http.controller;

import com.qbhy.apiboot.framework.http.response.Response;

abstract public class BaseController {
    protected  <T> Response ok(T data) {
        return Response.ok(data);
    }

    protected <T> Response fail(T data) {
        return Response.fail(data);
    }
}

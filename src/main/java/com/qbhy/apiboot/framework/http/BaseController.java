package com.qbhy.apiboot.framework.http;

import com.qbhy.apiboot.framework.http.response.Response;

abstract public class BaseController {

    public <T> Response ok(T data) {
        return Response.ok(data);
    }

    public <T> Response fail(T data) {
        return Response.fail(data);
    }

}

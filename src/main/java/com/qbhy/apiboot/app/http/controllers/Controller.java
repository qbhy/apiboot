package com.qbhy.apiboot.app.http.controllers;

import com.qbhy.apiboot.app.http.response.Response;

abstract public class Controller {

    <T> Response ok(T data) {
        return Response.ok(data);
    }

    <T> Response fail(T data) {
        return Response.fail(data);
    }

}

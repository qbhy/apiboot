package com.qbhy.apiboot.framework.contracts.http;

import com.qbhy.apiboot.framework.http.response.Response;

import javax.servlet.http.HttpServletRequest;

public interface Kernel {
    public Response handle(HttpServletRequest request);
}

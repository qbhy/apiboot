package com.qbhy.apiboot.framework.contracts.http;

import com.qbhy.apiboot.framework.http.response.Response;
import org.springframework.http.server.ServletServerHttpRequest;

public interface Kernel {
    public Response handle(ServletServerHttpRequest request);
}

package com.qbhy.apiboot.framework.contracts.http;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Dockable;
import org.springframework.http.server.ServletServerHttpRequest;

public interface HttpMiddleware extends Dockable {
    public void handle(ServletServerHttpRequest request);
}

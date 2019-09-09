package com.qbhy.apiboot.app.http;

import com.qbhy.apiboot.app.http.middlewares.ExampleMiddleware;
import com.qbhy.apiboot.app.http.middlewares.HelloMiddleware;
import com.qbhy.apiboot.framework.contracts.http.HttpMiddlewareRegister;
import com.qbhy.apiboot.framework.contracts.http.Kernel;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Dockable;
import com.qbhy.apiboot.framework.http.response.Response;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class HttpKernel implements Kernel, HttpMiddlewareRegister {

    /**
     * 普通中间件组
     *
     * @return middleware groups
     */
    @Override
    public Map<String, List<Dockable>> registerMiddlewareGroups() {
        Map<String, List<Dockable>> groups = new HashMap<>();

        groups.put("example", Collections.singletonList(
                new ExampleMiddleware()
        ));

        return groups;
    }

    /**
     * 全局中间件
     *
     * @return global middleware
     */
    @Override
    public List<Dockable> registerGlobalMiddlewares() {
        return Arrays.asList(
                new ExampleMiddleware(),
                new HelloMiddleware()
        );
    }

    @Override
    public Response handle(HttpServletRequest request) {
        return null;
    }
}

package com.qbhy.apiboot.app.http;

import com.qbhy.apiboot.framework.auth.AuthenticateMiddleware;
import com.qbhy.apiboot.app.http.middlewares.ExampleMiddleware;
import com.qbhy.apiboot.app.http.middlewares.ExampleGlobalMiddleware;
import com.qbhy.apiboot.framework.contracts.http.HttpMiddlewareRegister;
import com.qbhy.apiboot.framework.contracts.http.Kernel;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Dockable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HttpKernel implements Kernel, HttpMiddlewareRegister {

    @Autowired
    ExampleMiddleware exampleMiddleware;

    /**
     * 普通中间件组
     *
     * @return middleware groups
     */
    @Override
    public Map<String, List<Dockable>> registerMiddlewareGroups() {
        Map<String, List<Dockable>> groups = new HashMap<>();

        groups.put("example", Arrays.asList(exampleMiddleware));

        groups.put("jwt.auth", Arrays.asList(new AuthenticateMiddleware("jwt")));

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
                new ExampleGlobalMiddleware()
        );
    }
}

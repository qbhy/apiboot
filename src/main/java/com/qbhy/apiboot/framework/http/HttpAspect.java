package com.qbhy.apiboot.framework.http;

import com.qbhy.apiboot.app.http.HttpKernel;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Destination;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Dockable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.http.server.ServletServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class HttpAspect {

    private Map<String, List<Dockable>> middlewareGroups;
    private List<Dockable> globalMiddlewares;

    @Autowired
    public void setMiddlewareGroups(HttpKernel kernel) {
        this.middlewareGroups = kernel.registerMiddlewareGroups();
        this.globalMiddlewares = kernel.registerGlobalMiddlewares();
    }

    @Pointcut("execution(* com.qbhy.apiboot.app.http.controllers.*.*())")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletServerHttpRequest request = new ServletServerHttpRequest(
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest()
        );
//        Class targetClass = joinPoint.getTarget().getClass();
//        Method method = targetClass.getMethod(joinPoint.getSignature().getName());

        // 需要执行的中间件
        List<Dockable> middlewares = this.globalMiddlewares;

        // 通过管道执行中间件和控制器逻辑
        return (new HttpMiddlewarePipeline())
                .send(request)
                .through(middlewares)
                .then(traveler -> joinPoint.proceed());

    }
}

package com.qbhy.apiboot.framework.http;

import com.qbhy.apiboot.app.exceptions.Handler;
import com.qbhy.apiboot.app.http.HttpKernel;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Dockable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class HttpAspect {

    @Autowired
    Handler exceptionHandler;

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
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        // 需要执行的中间件
        List<Dockable> middlewares = this.globalMiddlewares;

        /**
         * 这里需要根据注解来添加普通中间件，还没写完
         Method method = targetClass.getMethod(joinPoint.getSignature().getName());
         **/
        Class targetClass = joinPoint.getTarget().getClass();
        Middleware targetClassAnnotation = (Middleware) targetClass.getAnnotation(Middleware.class);
        if (targetClassAnnotation != null) {

            middlewares = targetClassAnnotation.excludeGlobal() ? new ArrayList<>() : middlewares;

            if (this.middlewareGroups.containsKey(targetClassAnnotation.group())) {
                middlewares.addAll(this.middlewareGroups.get(targetClassAnnotation.group()));
            }
        }

        // 通过管道执行中间件和控制器逻辑
        return (new HttpMiddlewarePipeline())
                .send(request)
                .through(middlewares)
                .then(traveler -> joinPoint.proceed());
    }
}

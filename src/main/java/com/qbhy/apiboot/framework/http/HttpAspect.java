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
import java.lang.reflect.Method;
import java.util.*;

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

    @Pointcut("execution(* com.qbhy.apiboot.app.http.controllers..*(..))")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        // 需要执行的中间件
        List<Dockable> middlewares = this.globalMiddlewares;
        List<String> groups = new ArrayList<>();
        List<String> excludes = new ArrayList<>();

        Class targetClass = joinPoint.getTarget().getClass();
        Middleware targetClassAnnotation = (Middleware) targetClass.getAnnotation(Middleware.class);

        if (targetClassAnnotation != null) {
            middlewares = targetClassAnnotation.excludeGlobal() ? new ArrayList<>() : middlewares;
            groups.addAll(Arrays.asList(targetClassAnnotation.groups()));
            excludes.addAll(Arrays.asList(targetClassAnnotation.excludes()));
        }

        Method method = targetClass.getMethod(joinPoint.getSignature().getName());
        Middleware targetMethodAnnotation = method.getAnnotation(Middleware.class);
        if (targetMethodAnnotation != null) {
            if (targetMethodAnnotation.template() == MiddlewareTemplate.BLANK) {
                middlewares = targetMethodAnnotation.excludeGlobal() ? new ArrayList<>() : this.globalMiddlewares;
                groups = new ArrayList<>();
                excludes = new ArrayList<>();
            } else if (targetMethodAnnotation.template() == MiddlewareTemplate.EXTENDS) {
                middlewares = targetMethodAnnotation.excludeGlobal() ? new ArrayList<>() : this.globalMiddlewares;
            }
            groups.addAll(Arrays.asList(targetMethodAnnotation.groups()));
            excludes.addAll(Arrays.asList(targetMethodAnnotation.excludes()));
        }

        middlewares.addAll(getGroups(groups, excludes));

        // 通过管道执行中间件和控制器逻辑
        return (new HttpMiddlewarePipeline())
                .send(request)
                .through(middlewares)
                .then(traveler -> joinPoint.proceed());
    }

    /**
     * 获取中间件
     *
     * @param groups  分组名
     * @param exclude 排除的分组名
     * @return middleware
     */
    private List<Dockable> getGroups(List<String> groups, List<String> exclude) {
        List<Dockable> dockables = new ArrayList<>();

        for (String group : groups) {
            if (exclude.indexOf(group) == -1 && this.middlewareGroups.containsKey(group)) {
                dockables.addAll(this.middlewareGroups.get(group));
            }
        }

        return dockables;
    }
}

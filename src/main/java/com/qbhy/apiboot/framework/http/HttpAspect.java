package com.qbhy.apiboot.framework.http;

import com.qbhy.apiboot.app.exceptions.ExampleException;
import com.qbhy.apiboot.framework.http.response.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpAspect {
    @Pointcut("execution(* com.qbhy.apiboot.app.http.controllers.*.*())")
//    @Pointcut("@annotation(HttpMiddleware)")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) throws Throwable {

        //执行目标方法内容，获取执行结果

//        throw new ExampleException("测试抛异常");
//
        System.out.println("aroundPointcut 执行了");
//
//        return Response.ok("测试");

        //处理接口响应日志
        return joinPoint.proceed();
    }
}

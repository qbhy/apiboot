package com.qbhy.apiboot.framework.http;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpMiddleware {
    String value() default "";
}

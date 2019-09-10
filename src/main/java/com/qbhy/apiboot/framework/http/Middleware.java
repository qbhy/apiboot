package com.qbhy.apiboot.framework.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Middleware {
    String group();
    
    Class[] exclude() default {};

    /**
     * 是否排除全局中间件
     * @return 默认否
     */
    boolean excludeGlobal() default false;
}

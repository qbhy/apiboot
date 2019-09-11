package com.qbhy.apiboot.framework.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Middleware {
    /**
     * 组名字，允许多个组
     *
     * @return 中间件组
     */
    String[] groups();

    /**
     * 排除组里面的某些中间件如果有的话
     *
     * @return 需要排除的类名
     */
    String[] excludes() default {};

    /**
     * 是否排除全局中间件
     *
     * @return 默认否
     */
    boolean excludeGlobal() default false;

    /**
     * 优先级模板
     *
     * @return 默认类
     */
    MiddlewareTemplate template() default MiddlewareTemplate.CLASS_PRIORITY;
}

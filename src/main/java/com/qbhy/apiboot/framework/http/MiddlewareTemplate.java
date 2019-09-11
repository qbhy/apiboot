package com.qbhy.apiboot.framework.http;

public enum MiddlewareTemplate {
    /**
     * 空白，应用在 method 时忽略类注解
     */
    BLANK,

    /**
     * 继承类注解，此时 method 注解的 group、excludeGlobal、exclude 参数生效
     */
    EXTENDS,

    /**
     * 类注解优先，此时 method 注解的 group、exclude 参数生效
     */
    CLASS_PRIORITY;
}

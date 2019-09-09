package com.qbhy.apiboot.framework.contracts.kernel.pipeline;

public interface Stack {
    public Object next(Object passable) throws Throwable;
}

package com.qbhy.apiboot.framework.contracts.kernel.pipeline;

public interface Destination {
    public Object handle(Object traveler) throws Throwable;
}

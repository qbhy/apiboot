package com.qbhy.apiboot.framework.kernel.pipeline;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Destination;

abstract public class BaseDestination implements Destination {
    @Override
    public Object handle(Object traveler) {
        return null;
    }
}

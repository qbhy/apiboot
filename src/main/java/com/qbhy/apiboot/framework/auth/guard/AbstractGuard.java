package com.qbhy.apiboot.framework.auth.guard;

import com.qbhy.apiboot.framework.contracts.auth.Guard;

abstract public class AbstractGuard implements Guard {

    @Override
    public AbstractGuard clone() throws CloneNotSupportedException {
        return (AbstractGuard) super.clone();
    }
}

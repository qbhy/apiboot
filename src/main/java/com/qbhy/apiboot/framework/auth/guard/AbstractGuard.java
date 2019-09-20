package com.qbhy.apiboot.framework.auth.guard;

import com.qbhy.apiboot.framework.contracts.auth.AuthenticateAble;
import com.qbhy.apiboot.framework.contracts.auth.Guard;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractGuard implements Guard {

    protected AuthenticateAble user;

    protected Map<String, String> credentials = new HashMap<>();

    @Override
    public AbstractGuard clone() throws CloneNotSupportedException {
        AbstractGuard guard = (AbstractGuard) super.clone();

        guard.user = null;
        guard.credentials = new HashMap<>();

        return guard;
    }

    @Override
    public AuthenticateAble user(boolean get) {
        if (get) {
            return user;
        }
        return user();
    }
}

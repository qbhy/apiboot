package com.qbhy.apiboot.framework.auth;

import com.qbhy.apiboot.framework.contracts.auth.Guard;
import com.qbhy.apiboot.framework.contracts.auth.GuardProvider;
import com.qbhy.apiboot.framework.contracts.kernel.ServiceProvider;
import com.qbhy.apiboot.framework.util.ManualRegisterBeanUtil;
import com.qbhy.apiboot.framework.util.SpringContextUtil;

import java.util.HashMap;
import java.util.Map;

public class AuthServiceProvider implements ServiceProvider {
    public AuthServiceProvider() {
    }

    @Override
    public void register() {
        GuardProvider guardProvider = SpringContextUtil.getBean(GuardProvider.class);
        Map<String, Guard> guards = new HashMap<>();

        if (guardProvider != null) {
            guards = guardProvider.guards();
        }

        ManualRegisterBeanUtil.registerBean("authManager", AuthManager.class, guards);
    }
}

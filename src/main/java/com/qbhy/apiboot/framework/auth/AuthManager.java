package com.qbhy.apiboot.framework.auth;

import com.qbhy.apiboot.framework.contracts.auth.AuthenticateAble;
import com.qbhy.apiboot.framework.contracts.auth.Guard;
import com.qbhy.apiboot.framework.contracts.auth.GuardProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AuthManager {
    private Map<String, Guard> guards;
    private Map<HttpServletRequest, Guard> guardStack;
    private String defaultGuard;

    public AuthManager(GuardProvider provider) {
        guards = provider.guards();
        defaultGuard = provider.defaultGuard();
        guardStack = new HashMap<>();
    }

    public Map<String, Guard> getGuards() {
        return guards;
    }

    public Guard guard(String name) throws GuardNotFoundException, CloneNotSupportedException {
        Guard guard = this.guards.get(name);
        if (guard != null) {
            return guard.clone();
        }
        throw new GuardNotFoundException("guard not found! guard:" + name);
    }

    public AuthManager setGuard(HttpServletRequest request, Guard guard) {
        guardStack.put(request, guard);
        return this;
    }

    public AuthManager remove(HttpServletRequest request) {
        guardStack.remove(request);
        return this;
    }

    public AuthenticateAble user(HttpServletRequest request) throws Throwable {
        Guard guard = guardStack.get(request);

        if (guard != null) {
            return guard.user();
        }

        try {
            guard = guard(defaultGuard);
            setGuard(request, guard);
            return guard.parseCredentials(request).user();
        } catch (Throwable throwable) {
            return null;
        }
    }
}

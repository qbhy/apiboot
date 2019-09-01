package com.qbhy.apiboot.framework.auth;

import com.qbhy.apiboot.framework.contracts.auth.Guard;

import java.util.Map;

public class AuthManager {
    private Map<String, Guard> guards;

    public AuthManager(Map<String, Guard> guards) {
        this.guards = guards;
    }

    public Guard guard(String name) throws GuardNotFoundException {
        Guard guard = this.guards.get(name);
        if (guard != null) {
            return guard;
        }
        throw new GuardNotFoundException("guard not found! guard:" + name);
    }

}

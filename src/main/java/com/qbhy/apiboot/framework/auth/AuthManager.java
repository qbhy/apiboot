package com.qbhy.apiboot.framework.auth;

import com.qbhy.apiboot.framework.contracts.auth.AuthenticateAble;
import com.qbhy.apiboot.framework.contracts.auth.Guard;
import com.qbhy.apiboot.framework.contracts.auth.GuardProvider;
import com.qbhy.apiboot.framework.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AuthManager {
    private Map<String, Guard> guards;
    private String defaultGuard;

    // 临时存储用户信息，控制器方法执行完毕后会释放对应数据
    private Map<String, AuthenticateAble> users;

    public AuthManager(GuardProvider provider) {
        guards = provider.guards();
        defaultGuard = provider.defaultGuard();
        users = new HashMap<>();
    }

    public Map<String, AuthenticateAble> getUsers() {
        return users;
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

    public AuthenticateAble user(String guardName) {
        try {
            HttpServletRequest request = RequestUtil.request();
            Guard guard = guard(guardName != null ? guardName : defaultGuard);

            AuthenticateAble user = users.remove(guard.credentialsKey(request));
            if (user != null) {
                return user;
            }

            return guard.parseCredentials(request).user();
        } catch (Throwable throwable) {
            // 正常来说是 parseCredentials 抛出的忽略该异常
            return null;
        }
    }

    public AuthenticateAble user() {
        return user(defaultGuard);
    }
}

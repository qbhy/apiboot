package com.qbhy.apiboot.framework.auth;

import com.qbhy.apiboot.framework.contracts.auth.AuthenticateAble;
import com.qbhy.apiboot.framework.contracts.auth.Guard;
import com.qbhy.apiboot.framework.contracts.auth.GuardProvider;
import com.qbhy.apiboot.framework.support.Manager;
import com.qbhy.apiboot.framework.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AuthManager extends Manager<Guard> {

    /**
     * 设置驱动供应商
     */
    @Override
    public void setDriverProviders() {

    }

    /**
     * 获取默认驱动
     *
     * @return 驱动名
     */
    @Override
    protected String defaultDriver() {
        return defaultGuard;
    }

    private String defaultGuard;

    // 临时存储用户信息，控制器方法执行完毕后会释放对应数据
    private Map<String, AuthenticateAble> users;

    public AuthManager(GuardProvider provider) {
        drivers = provider.guards();
        defaultGuard = provider.defaultGuard();
        users = new HashMap<>();
    }

    public Map<String, AuthenticateAble> getUsers() {
        return users;
    }

    public AuthenticateAble user(String guardName) {
        try {
            HttpServletRequest request = RequestUtil.request();
            Guard guard = driver(guardName);

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

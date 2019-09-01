package com.qbhy.apiboot.framework.contracts.auth;

import java.util.Map;

public interface Guard {

    /**
     * Determine if the current user is authenticated.
     *
     * @return bool
     */
    public boolean check();

    /**
     * Determine if the current user is a guest.
     *
     * @return bool
     */
    public boolean guest();

    /**
     * Get the currently authenticated user.
     *
     * @return UserContract
     */
    public AuthenticateAble user();

    /**
     * Get the ID for the currently authenticated user.
     *
     * @return int|string|null
     */
    public Object id();

    /**
     * Validate a user's credentials.
     *
     * @param credentials 登录凭证
     * @return bool
     */
    public boolean validate(Map<String, String> credentials);

    /**
     * Set the current user.
     *
     * @param user 用户实例
     */
    public void setUser(AuthenticateAble user);
}

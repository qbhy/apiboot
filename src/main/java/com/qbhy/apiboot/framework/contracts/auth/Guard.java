package com.qbhy.apiboot.framework.contracts.auth;

import java.util.Map;

public interface Guard extends Cloneable {

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

    public Guard parseCredentials(Object credentials) throws Throwable;

    /**
     * Set the current user.
     *
     * @param user 用户实例
     */
    public void setUser(AuthenticateAble user);

    public Object login(AuthenticateAble user) throws Throwable;

    public Guard clone() throws CloneNotSupportedException;
}

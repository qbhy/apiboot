package com.qbhy.apiboot.framework.contracts.auth;

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
     * 直接取 guard 属性
     *
     * @param get 是否直接从属性中获取
     * @return UserContract
     */
    public AuthenticateAble user(boolean get);

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

    /**
     * 用户登录
     *
     * @param user 用户
     * @return token 之类的
     * @throws Throwable 异常
     */
    public Object login(AuthenticateAble user) throws Throwable;

    /**
     * 克隆 guard 实例
     *
     * @return 克隆后的实例
     * @throws CloneNotSupportedException 异常
     */
    public Guard clone() throws CloneNotSupportedException;


    /**
     * 凭证唯一key
     *
     * @return key
     */
    public String credentialsKey(Object credentials);

}

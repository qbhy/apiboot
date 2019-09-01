package com.qbhy.apiboot.framework.contracts.auth;

import java.util.Map;

public interface UserProvider {
    /**
     * Retrieve a user by their unique identifier.
     *
     * @param identifier ID
     * @return user
     */
    public AuthenticateAble retrieveById(String identifier);

    /**
     * Retrieve a user by their unique identifier and "remember me" token.
     *
     * @param identifier ID
     * @param token      token
     * @return \Illuminate\Contracts\Auth\Authenticatable|null
     */
    public AuthenticateAble retrieveByToken(String identifier, String token);

    /**
     * Update the "remember me" token for the given user in storage.
     *
     * @param user  用户实例
     * @param token token
     */
    public void updateRememberToken(AuthenticateAble user, String token);

    /**
     * Retrieve a user by the given credentials.
     *
     * @param credentials 登录凭证
     * @return user
     */
    public AuthenticateAble retrieveByCredentials(Map<String, String> credentials);

    /**
     * Validate a user against the given credentials.
     *
     * @param user        用户实例
     * @param credentials 登录凭证
     * @return bool
     */
    public boolean validateCredentials(AuthenticateAble user, Map<String, String> credentials);
}

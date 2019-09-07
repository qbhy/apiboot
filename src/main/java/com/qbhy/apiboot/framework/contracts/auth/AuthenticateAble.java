package com.qbhy.apiboot.framework.contracts.auth;

import java.io.Serializable;

public interface AuthenticateAble extends Serializable {
    /**
     * Get the name of the unique identifier for the user.
     *
     * @return string
     */
    public String getAuthIdentifierName();

    /**
     * Get the unique identifier for the user.
     *
     * @return mixed
     */
    public Object getAuthIdentifier();

    /**
     * Get the password for the user.
     *
     * @return string
     */
    public String getAuthPassword();

    /**
     * Get the token value for the "remember me" session.
     *
     * @return string
     */
    public String getRememberToken();

    /**
     * Set the token value for the "remember me" session.
     *
     * @param  value token
     */
    public void setRememberToken(String value);

    /**
     * Get the column name for the "remember me" token.
     *
     * @return string
     */
    public String getRememberTokenName();

}

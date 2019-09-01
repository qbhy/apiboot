package com.qbhy.apiboot.framework.auth;

import com.qbhy.apiboot.framework.contracts.auth.Guard;
import com.qbhy.apiboot.framework.contracts.auth.AuthenticateAble;
import com.qbhy.apiboot.framework.contracts.auth.UserProvider;

import java.util.Map;

public class JwtGuard implements Guard {
    private UserProvider userProvider;

    private AuthenticateAble user;

    public JwtGuard(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @Override
    public boolean check() {
        return this.user() != null;
    }

    @Override
    public boolean guest() {
        return this.user() == null;
    }

    @Override
    public AuthenticateAble user() {
        if (this.user != null) {
            return user;
        }

        return null;
    }

    @Override
    public Object id() {
        AuthenticateAble user = this.user();
        return user != null ? user.getAuthIdentifier() : null;
    }

    @Override
    public boolean validate(Map<String, String> credentials) {
        return credentials.get("token") != null;
    }

    @Override
    public void setUser(AuthenticateAble user) {
        this.user = user;
    }
}

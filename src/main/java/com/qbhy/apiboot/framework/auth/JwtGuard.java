package com.qbhy.apiboot.framework.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qbhy.apiboot.framework.auth.guard.AbstractGuard;
import com.qbhy.apiboot.framework.contracts.auth.AuthenticateAble;
import com.qbhy.apiboot.framework.contracts.auth.Guard;
import com.qbhy.apiboot.framework.contracts.auth.UserProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class JwtGuard extends AbstractGuard {
    private UserProvider userProvider;

    private Algorithm algorithm;

    private AuthenticateAble user;

    private Map<String, String> credentials = new HashMap<>();

    public JwtGuard(UserProvider userProvider, Algorithm algorithmHS) {
        this.userProvider = userProvider;
        this.algorithm = algorithmHS;
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
        if (user != null) {
            return user;
        }

        return user(credentials.get("token"));
    }

    @Override
    public Object id() {
        AuthenticateAble user = this.user();
        return user != null ? user.getAuthIdentifier() : null;
    }

    @Override
    public Guard parseCredentials(Object credentialsOrigin) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) credentialsOrigin;

        // 从 headers 中获取 token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            credentials.put("token", token.substring(7));

        }

        if (credentials.get("token") == null) {
            throw new MissingCredentialException("缺少身份凭证令牌!");
        }

        return this;
    }

    @Override
    public void setUser(AuthenticateAble user) {
        this.user = user;
    }

    /**
     * 根据 token 获取用户
     *
     * @param token token
     * @return 用户
     */
    public AuthenticateAble user(String token) {
        try {
            DecodedJWT jwt = JWT.require(algorithm).withIssuer("auth0").build().verify(token);
            credentials.put("id", jwt.getClaim("id").asString());
            return user = userProvider.retrieveByCredentials(credentials);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    @Override
    public String login(AuthenticateAble user) {
        if (user != null) {
            this.user = user;
            return JWT.create()
                    .withIssuer("auth0")
                    .withClaim("id", user.getAuthIdentifier().toString())
                    .sign(algorithm);
        }

        return null;
    }
}

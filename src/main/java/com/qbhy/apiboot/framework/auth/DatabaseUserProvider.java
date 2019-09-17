package com.qbhy.apiboot.framework.auth;

import com.qbhy.apiboot.app.repositories.UserRepository;
import com.qbhy.apiboot.framework.contracts.auth.RememberToken;
import com.qbhy.apiboot.framework.contracts.auth.RetrieveAble;
import com.qbhy.apiboot.framework.contracts.auth.AuthenticateAble;
import com.qbhy.apiboot.framework.contracts.auth.UserProvider;

import java.util.Map;

public class DatabaseUserProvider implements UserProvider {
    private UserRepository userRepository;

    public DatabaseUserProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticateAble retrieveById(String identifier) {
        return userRepository.findById(Long.valueOf(identifier)).orElse(null);
    }

    @Override
    public AuthenticateAble retrieveByToken(String identifier, String token) {
        if (userRepository instanceof RememberToken) {
            return ((RememberToken) userRepository).retrieveByToken(identifier, token);
        }
        return null;
    }

    @Override
    public void updateRememberToken(AuthenticateAble user, String token) {
        if (userRepository instanceof RememberToken) {
            ((RememberToken) userRepository).updateRememberToken(user, token);
        }
    }

    @Override
    public AuthenticateAble retrieveByCredentials(Map<String, String> credentials) {
        if (userRepository instanceof RetrieveAble) {
            return ((RetrieveAble) userRepository).retrieveByCredentials(credentials);
        }
        return userRepository.findById(Long.parseLong(credentials.get("id"))).orElse(null);
    }

    @Override
    public boolean validateCredentials(AuthenticateAble user, Map<String, String> credentials) {
        return false;
    }
}

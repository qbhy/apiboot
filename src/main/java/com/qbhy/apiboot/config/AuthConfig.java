package com.qbhy.apiboot.config;

import com.qbhy.apiboot.app.repositories.UserRepository;
import com.qbhy.apiboot.framework.auth.AuthManager;
import com.qbhy.apiboot.framework.auth.DatabaseUserProvider;
import com.qbhy.apiboot.framework.auth.JwtGuard;
import com.qbhy.apiboot.framework.contracts.auth.Guard;
import com.qbhy.apiboot.framework.contracts.auth.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AuthConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean(name = "database.userProvider")
    public UserProvider userProvider() {
        return new DatabaseUserProvider(userRepository);
    }

    @Bean(name = "jwt.guard")
    public JwtGuard jwtGuard() {
        return new JwtGuard(null);
    }

    @Bean
    public AuthManager authManager() {
        Map<String, Guard> guards = new HashMap<>();
        return new AuthManager(guards);
    }


}

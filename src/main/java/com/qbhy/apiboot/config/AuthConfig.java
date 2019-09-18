package com.qbhy.apiboot.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.qbhy.apiboot.app.repositories.UserRepository;
import com.qbhy.apiboot.framework.auth.DatabaseUserProvider;
import com.qbhy.apiboot.framework.auth.JwtGuard;
import com.qbhy.apiboot.framework.contracts.auth.Guard;
import com.qbhy.apiboot.framework.contracts.auth.GuardProvider;
import com.qbhy.apiboot.framework.contracts.auth.UserProvider;
import com.qbhy.apiboot.framework.foundation.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthConfig {

    private String defaultGuard = "jwt";

    private String jwtSecret = "apiboot";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DatabaseUserProvider databaseUserProvider;

    @Bean
    public DatabaseUserProvider userProvider() {
        return new DatabaseUserProvider(userRepository);
    }

    @Bean
    public GuardProvider guards() {
        return new GuardProvider() {
            @Override
            public Map<String, Guard> guards() {
                Map<String, Guard> guards = new HashMap<>();

                // 添加guard
                guards.put("jwt", new JwtGuard(databaseUserProvider, Algorithm.HMAC256(jwtSecret)));

                return guards;
            }

            @Override
            public String defaultGuard() {
                return defaultGuard;
            }
        };
    }
}

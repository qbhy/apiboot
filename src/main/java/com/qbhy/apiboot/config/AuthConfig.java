package com.qbhy.apiboot.config;

import com.qbhy.apiboot.app.repositories.UserRepository;
import com.qbhy.apiboot.framework.auth.DatabaseUserProvider;
import com.qbhy.apiboot.framework.auth.JwtGuard;
import com.qbhy.apiboot.framework.contracts.auth.Guard;
import com.qbhy.apiboot.framework.contracts.auth.GuardProvider;
import com.qbhy.apiboot.framework.contracts.auth.UserProvider;
import com.qbhy.apiboot.framework.util.SpringContextUtil;
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
    public DatabaseUserProvider userProvider() {
        return new DatabaseUserProvider(userRepository);
    }

    @Bean
    public GuardProvider guards() {
        return () -> {
            Map<String, Guard> guards = new HashMap<>();

            // 添加guard
            guards.put("jwt", new JwtGuard((UserProvider) SpringContextUtil.getBean("database.userProvider")));

            return guards;
        };
    }
}

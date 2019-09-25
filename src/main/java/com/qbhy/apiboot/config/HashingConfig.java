package com.qbhy.apiboot.config;

import com.qbhy.apiboot.framework.contracts.hashing.SecretProvider;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "hashing")
public class HashingConfig implements SecretProvider {
    private String secret = "apiboot";

    @Override
    public String get() {
        return secret;
    }
}

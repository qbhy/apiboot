package com.qbhy.apiboot.config;

import com.qbhy.apiboot.framework.contracts.hashing.HashOptions;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class HashingConfig implements HashOptions {
    private String secret = "apiboot";

}

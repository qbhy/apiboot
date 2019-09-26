package com.qbhy.apiboot.config;

import com.qbhy.apiboot.framework.contracts.kernel.SecretProvider;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@Data
@ConfigurationProperties(prefix = "app")
public class AppConfig implements SecretProvider {
    /**
     * app密钥
     */
    private String secret = "apiboot";

    @Override
    public String get() {
        return secret;
    }

    @Bean(name = "applicationEventMulticaster")
    public SimpleApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }
}

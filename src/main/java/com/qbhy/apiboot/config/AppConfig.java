package com.qbhy.apiboot.config;

import com.qbhy.apiboot.framework.contracts.kernel.SecretProvider;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@Data
public class AppConfig implements SecretProvider {
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

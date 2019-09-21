package com.qbhy.apiboot.config;

import com.qbhy.apiboot.framework.http.middlewares.cross.Cross;
import com.qbhy.apiboot.framework.http.middlewares.cross.CrossProvider;
import com.qbhy.apiboot.framework.http.middlewares.cross.Crosses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class CrossConfig {
    @Bean
    public CrossProvider crossProvider() {
        return () -> new Crosses()
                .add("localhost:8000")
                .add(new Cross("localhost:8080", new HttpMethod[]{
                        HttpMethod.GET,
                        HttpMethod.POST,
                }));
    }
}

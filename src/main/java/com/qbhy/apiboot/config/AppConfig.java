package com.qbhy.apiboot.config;

import com.qbhy.apiboot.framework.auth.AuthServiceProvider;
import com.qbhy.apiboot.framework.contracts.kernel.ServiceProvider;
import com.qbhy.apiboot.framework.contracts.kernel.ServiceRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
public class AppConfig {
    /**
     * 在这里添加服务提供者
     */
    @Bean
    public ServiceRegister registerProviders() {
        return () -> {
            ArrayList<ServiceProvider> serviceProviders = new ArrayList<>();

            // 在此处添加服务提供者
            serviceProviders.add(new AuthServiceProvider());

            // 注册服务
            serviceProviders.forEach(ServiceProvider::register);

            return serviceProviders;
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }
        };
    }
}

package com.qbhy.apiboot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) throws UnknownHostException {
        RedisTemplate<Object, Object> redis = new RedisTemplate<>();
        redis.setConnectionFactory(factory);
        redis.setKeySerializer(keySerializer());
        redis.setHashKeySerializer(keySerializer());
        redis.setValueSerializer(valueSerializer());
        redis.setHashValueSerializer(valueSerializer());
        return redis;
    }

    @Bean
    @ConditionalOnMissingBean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) throws UnknownHostException {
        StringRedisTemplate redis = new StringRedisTemplate();
        redis.setConnectionFactory(factory);
        return redis;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}

package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Value("${isSSL}")
    private Boolean isSSL;
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        var redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName("localhost");
        redisConfig.setPort(6379);
        redisConfig.setPassword("p@55w0rd");
        JedisConnectionFactory factory = new JedisConnectionFactory(redisConfig);
        if(isSSL){
            factory.setUseSsl(true);
        }
        return factory;
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        var redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        return redisTemplate;
    }
}

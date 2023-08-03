package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Set;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableCaching(proxyTargetClass = true)
@RequiredArgsConstructor
public class RedisCacheConfig {

    private static final String KMS_UTILIZATION = "KMS_UTILIZATION";
    private static final String ROOMS = "ROOMS";
    private static final String USERS = "USERS";

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory jedisConnectionFactory) {
        var redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofHours(500))
                .serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.builder(jedisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .initialCacheNames(Set.of(
                        KMS_UTILIZATION,
                        USERS,
                        ROOMS))
                .build();
    }

    @Bean
    public Cache kmsUtilizationRedisCache(CacheManager redisCacheManager) {
        return redisCacheManager.getCache(KMS_UTILIZATION);
    }

    @Bean
    public Cache roomsRedisCache(CacheManager redisCacheManager) {
        return redisCacheManager.getCache(ROOMS);
    }

    @Bean
    public Cache usersRedisCache(CacheManager redisCacheManager) {
        return redisCacheManager.getCache(USERS);
    }
}

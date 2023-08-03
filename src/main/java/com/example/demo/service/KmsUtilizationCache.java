package com.example.demo.service;

import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

@Component
public class KmsUtilizationCache extends RedisCache {
    public KmsUtilizationCache(Cache kmsUtilizationRedisCache) {
        super(kmsUtilizationRedisCache);
    }
}
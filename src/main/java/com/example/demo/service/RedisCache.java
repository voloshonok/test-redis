package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;

@Slf4j
@RequiredArgsConstructor
public class RedisCache {

    private final Cache cache;

    public <T> void put(String key, T entity) {
        try {
             cache.put(key, entity);
        } catch (Exception e) {
            log.error("failed to save entity to redis. Cache - {}. Error - {}.", cache.getName(), e.getMessage());
        }
    }

    public void evict(String key) {
        try {
            cache.evict(key);
        } catch (Exception e) {
            log.error("failed to remove entity from redis. Cache - {}. Error - {}.", cache.getName(), e.getMessage());
        }
    }

    public <T> T get(String key, Class<T> clazz) {
        try {
            return cache.get(key, clazz);
        } catch (Exception e) {
            log.error("failed to get entity from redis. Cache - {}. Error - {}.", cache.getName(), e.getMessage());
            return null;
        }
    }
}

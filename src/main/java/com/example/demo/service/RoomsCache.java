package com.example.demo.service;

import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

@Component
public class RoomsCache extends RedisCache {
    public RoomsCache(Cache roomsRedisCache) { super(roomsRedisCache); }
}
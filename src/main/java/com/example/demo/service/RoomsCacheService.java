package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomsCacheService {
    private final RoomsCache roomsCache;

    public void add(String key, RoomCacheRecord entity) {
        roomsCache.put(key, entity);
    }

    public RoomCacheRecord get(String key) {
        return roomsCache.get(key, RoomCacheRecord.class);
    }

    public void delete(String key) {
        roomsCache.evict(key);
    }
}

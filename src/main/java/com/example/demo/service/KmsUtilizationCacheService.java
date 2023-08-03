package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KmsUtilizationCacheService {
    private final KmsUtilizationCache kmsUtilizationCache;

    public void add(String key, String entity) {
        kmsUtilizationCache.put(key, entity);
    }

    public String get(String key) {
        return kmsUtilizationCache.get(key, String.class);
    }

    public void delete(String key) {
        kmsUtilizationCache.evict(key);
    }
}

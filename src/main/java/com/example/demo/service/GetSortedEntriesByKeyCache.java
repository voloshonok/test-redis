package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetSortedEntriesByKeyCache {
    private final RedisTemplate<String, String> redisTemplate;

    public Map<String, Long> getSortedEntriesByKeyCache(String cacheName) {

        Map<String, String> result = Objects.requireNonNull(redisTemplate.keys(cacheName + "*"))
                .parallelStream()
                .collect(Collectors.toMap(it -> it, it -> redisTemplate.opsForValue().get(it)));

        return result.entrySet().stream()
                .map(it -> {
                    String key = it.getKey();
                    key = key.replace(cacheName, "")
                            .replace(":", "")
                            .replace("/", ":");
                    String value = it.getValue().replace("\"", "");
                    return Map.entry(key, Long.parseLong(value));
                })
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }
}

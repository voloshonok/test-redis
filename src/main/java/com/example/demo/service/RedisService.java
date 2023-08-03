package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RoomsCacheService roomsCacheService;
    private final AppropriateKmsServerService appropriateKmsServerService;
    private final GetSortedEntriesByKeyCache getSortedEntriesByKeyCache;
    private final KmsUtilizationCacheService kmsUtilizationCacheService;

    public void call() {

        Map<String, Long> sortedEntriesByKeyCache = getSortedEntriesByKeyCache.getSortedEntriesByKeyCache("KMS_UTILIZATION");
        var appropriateKmsServerIpPort = appropriateKmsServerService.getAppropriateKmsServerService(
                sortedEntriesByKeyCache);
        kmsUtilizationCacheService.add("10.41.4.241/8888", String.valueOf(3));
        kmsUtilizationCacheService.add("10.43.4.241/8888", String.valueOf(2));
        kmsUtilizationCacheService.add("10.43.9.241/8888", String.valueOf(-1));

        kmsUtilizationCacheService.add(appropriateKmsServerIpPort.replace(":", "/"), String.valueOf(9));
        System.out.println(kmsUtilizationCacheService.get("10.43.9.241/8888"));
//        roomsCacheService.add("asd", new RoomCacheRecord("date"));

    }
}

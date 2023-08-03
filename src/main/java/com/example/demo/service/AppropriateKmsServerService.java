package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AppropriateKmsServerService {

    public String getAppropriateKmsServerService(Map<String, Long> mapOfKmsServers) {
        return mapOfKmsServers.keySet().stream()
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}

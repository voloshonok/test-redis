package com.example.demo.controller;

import com.example.demo.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @GetMapping(value = "/call")
    @ResponseStatus(OK)
    public void call() {
        redisService.call();
    }
}

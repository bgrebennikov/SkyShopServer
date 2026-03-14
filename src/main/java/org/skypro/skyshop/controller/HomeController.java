package org.skypro.skyshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    Map<String, Object> home() {
        return Map.of("message", "Добро пожаловать на сервер Бориса Азазовского!");
    }
}

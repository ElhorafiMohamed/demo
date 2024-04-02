package com.example.demo.controllers;

import com.example.demo.services.DemoService;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class DemoController {
    private final DemoService demoService;
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/admin_only")
    public ResponseEntity<String> admin_only() {
        return ResponseEntity.ok("Hello from admin only url");
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        logger.info("Received request to /hello");
        return demoService.infos();
    }
}

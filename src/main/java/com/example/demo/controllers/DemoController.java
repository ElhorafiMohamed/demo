package com.example.demo.controllers;

import com.example.demo.services.DemoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class DemoController {
    private final DemoService demoService;


    @GetMapping("/admin_only")
    public ResponseEntity<String> admin_only() {
        return ResponseEntity.ok("Hello from admin only url");
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return demoService.infos();
    }
}

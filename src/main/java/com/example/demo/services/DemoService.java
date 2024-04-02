package com.example.demo.services;

import com.example.demo.utils.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {

public final PasswordGenerator dateTimeUtil;
    public ResponseEntity<String> infos() {
        System.out.println("Formatted date: " + dateTimeUtil.generateTemporaryPassword());
        return ResponseEntity.ok("Hello from user only url");
    }

}

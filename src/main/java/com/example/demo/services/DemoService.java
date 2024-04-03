package com.example.demo.services;

import com.example.demo.dtos.EmailDetails;
import com.example.demo.email.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {
    private final EmailSender emailSender;
    public ResponseEntity<String> infos() {
        emailSender.sendSimpleMail();

        return ResponseEntity.ok("Hello from user only url");
    }

    public ResponseEntity<String> users() {
        return ResponseEntity.ok("Hello from user only url");
    }
}

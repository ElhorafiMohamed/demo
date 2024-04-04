package com.example.demo.controllers;


import com.example.demo.dao.AuthenticationDao;
import com.example.demo.dtos.ChangePassDTO;
import com.example.demo.exceptions.AuthenticationResponse;
import com.example.demo.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationDao authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return authService.authenticate(request);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<AuthenticationResponse> changePassword(@RequestBody ChangePassDTO request) {
        return authService.changePassword(request);
    }

    @PostMapping("/getToken")
    public ResponseEntity<AuthenticationResponse> getToken(@RequestBody User request) {
        return authService.getToken(request);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody User request) {
        return authService.authenticate(request);
    }
}

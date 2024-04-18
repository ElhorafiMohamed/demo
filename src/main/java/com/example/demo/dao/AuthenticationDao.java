package com.example.demo.dao;

import com.example.demo.dtos.ChangePassDTO;
import com.example.demo.entities.User;
import com.example.demo.exceptions.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationDao {
    ResponseEntity<AuthenticationResponse> register(User request);

    User createUserFromRequest(User request);

    ResponseEntity<AuthenticationResponse> changePassword(ChangePassDTO request);

    ResponseEntity<AuthenticationResponse> authenticate(User request);

    void revokeAllTokenByUser(User user);

    void saveUserToken(String jwt, User user);

    ResponseEntity<AuthenticationResponse> getToken(User request);
}

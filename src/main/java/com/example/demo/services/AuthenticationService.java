package com.example.demo.services;


import com.example.demo.dao.AuthenticationDao;
import com.example.demo.dtos.ChangePassDTO;
import com.example.demo.exceptions.AuthenticationResponse;
import com.example.demo.entities.Token;
import com.example.demo.entities.User;
import com.example.demo.repositories.TokenRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationDao {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<AuthenticationResponse> register(User request) {
        try {
            if (repository.findByUsername(request.getUsername()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthenticationResponse(null, "User already exists"));
            }
            User user = createUserFromRequest(request);
            user = repository.save(user);
            String jwt = jwtService.generateToken(user);
            saveUserToken(jwt, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticationResponse(jwt, "User registration was successful"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthenticationResponse(null, "An error occurred during user registration"));
        }
    }

    public User createUserFromRequest(User request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
    }

    public ResponseEntity<AuthenticationResponse> changePassword(ChangePassDTO request) {
        try {
            User user = repository.findByUsername(request.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getOldPassword()
                    )
            );
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user = repository.save(user);
            String jwt = jwtService.generateToken(user);
            revokeAllTokenByUser(user);
            saveUserToken(jwt, user);

            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticationResponse(jwt, "User password was successful changed"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthenticationResponse(null, "An error occurred during user registration"));
        }
    }

    public ResponseEntity<AuthenticationResponse> authenticate(User request) {
        try {
            User user = repository.findByUsername(request.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            String jwt = jwtService.generateToken(user);
            revokeAllTokenByUser(user);
            saveUserToken(jwt, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticationResponse(jwt, "User password was successful changed"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthenticationResponse(null, "An error occurred during user registration"));
        }
    }

    public void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t -> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }

    public void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public ResponseEntity<AuthenticationResponse> getToken(User request) {
        try {
            User user = repository.findByUsername(request.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
            List<Token> tokens = tokenRepository.findAllTokensByUser(user.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticationResponse(tokens.get(0).getToken(), "User password was successful changed"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthenticationResponse(null, "An error occurred during user registration"));
        }
    }
}

package com.example.demo.configs;

import com.example.demo.dtos.UserProps;
import com.example.demo.entities.User;
import com.example.demo.enums.Role;
import com.example.demo.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@Slf4j
public class DataLoader {
    public final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final UserProps userProps;

    @Bean
    public ApplicationRunner init() {
        return args -> {
            loadAdmin();
        };
    }

    public void loadAdmin() {
        var user = User.builder()
                .firstName(userProps.getFirstName())
                .lastName(userProps.getLastName())
                .password(passwordEncoder.encode(userProps.getPassword()))
                .role(Role.SUP_ADMIN)
                .build();

        System.out.println(user);
        authenticationService.register(user);
    }


}

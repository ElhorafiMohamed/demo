package com.example.demo.dtos;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ConfigurationProperties("admin")
@Configuration
public class UserProps {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private String role;
}

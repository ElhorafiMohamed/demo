package com.example.demo.configs;

import com.example.demo.dao.AuthenticationDao;
import com.example.demo.dao.FileDao;
import com.example.demo.dtos.UserProps;
import com.example.demo.entities.User;
import com.example.demo.enums.Role;
import com.example.demo.services.AuthenticationService;
import com.example.demo.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.nio.file.Paths;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@Slf4j
public class DataLoader {
    public final AuthenticationDao authenticationService;
    public final FileDao fileDao;
    public final FileUtil fileUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserProps userProps;

    @Bean
    public ApplicationRunner init() {
        return args -> {
            loadAdmin();
            fileUtil.createDossier("upload");

            fileUtil.createDossier("upload/csv");
            fileUtil.createDossier("upload/document");
            fileUtil.createDossier("upload/pdf");
            fileUtil.createDossier("upload/plain");
            fileUtil.createDossier("upload/png");
            fileUtil.createDossier("upload/sheet");
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

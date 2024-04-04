package com.example.demo.dao;


import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface DemoDao {
    ResponseEntity<String> infos() throws MessagingException, IOException;

    ResponseEntity<String> users();
}

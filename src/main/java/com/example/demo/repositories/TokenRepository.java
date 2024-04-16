package com.example.demo.repositories;

import com.example.demo.entities.datasource1.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = "select t.* from Token t where user_id = 1 and is_logged_Out = false", nativeQuery = true)
    List<Token> findAllTokensByUser(Integer userId);

    Optional<Token> findByToken(String token);
}

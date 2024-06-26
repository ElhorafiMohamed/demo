package com.example.demo.repositories;

import com.example.demo.entities.User;
import com.example.demo.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByRole(Role role);
}

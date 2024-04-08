package com.example.demo.repositories;

import com.example.demo.entities.AttachmentType;
import com.example.demo.entities.File;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AttachementTypeRepository extends JpaRepository<AttachmentType, Integer> {
    Optional<AttachmentType> findByAbrv(String abrv);
}

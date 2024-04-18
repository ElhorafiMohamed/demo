package com.example.demo.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class DataBaseUtil {

    private final EntityManager entityManager;

    public Set<String> getAllTableNames() {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        Set<String> tableNames = entities.stream()
                .map(entityType -> entityType.getName())
                .collect(Collectors.toSet());
        return tableNames;
    }
}
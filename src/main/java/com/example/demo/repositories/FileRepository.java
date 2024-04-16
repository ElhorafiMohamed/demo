package com.example.demo.repositories;

import com.example.demo.entities.datasource1.AttachmentType;
import com.example.demo.entities.datasource1.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FileRepository extends JpaRepository<File, Integer> {
    @Override
    List<File> findAll();
    List<File> findByAttachmentType(AttachmentType attachmentType);

    @Query(value = "UPDATE File f SET f.isDeleted = :isDeleted", nativeQuery = true)
    void updateAllFiles(boolean isDeleted);
}

package com.example.demo.dao;


import com.example.demo.entities.File;
import com.example.demo.exceptions.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface FileDao {
     ResponseEntity<ResponseMessage> save(MultipartFile[] files);
     ResponseEntity<ResponseMessage> deleteAll();
     List<File> getAllFichier() throws IOException;
     List<File> getAllFichierByType(String s) throws IOException;
}

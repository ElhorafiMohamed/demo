package com.example.demo.dao;


import com.example.demo.exceptions.ResponseMessage;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;


public interface FileDao {
    public ResponseEntity<ResponseMessage> save(MultipartFile[] files);
    public ResponseEntity<ResponseMessage> deleteAll();
    public List<String> getAllFichierByType(String s) throws IOException;
    public List<String> getAllFichier() throws IOException;
}

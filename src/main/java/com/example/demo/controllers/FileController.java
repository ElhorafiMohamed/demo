package com.example.demo.controllers;


import com.example.demo.dao.FileDao;
import com.example.demo.exceptions.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
public class FileController {
    FileDao storageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files){
        return storageService.save(files);
    }

    @GetMapping("/deletes")
    public ResponseEntity<ResponseMessage> deleteFiles(){
        return storageService.deleteAll();
    }

    @GetMapping("/files/{typeFichier:.+}")
    public ResponseEntity<List<String>> getAllFichierByType(@PathVariable String typeFichier) throws IOException {
        List<String> fileInfos = storageService.getAllFichierByType(typeFichier);
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/getAllFichier")
    public ResponseEntity<List<String>> getAllFichier() throws IOException {
        List<String> fileInfos = storageService.getAllFichier();
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
}

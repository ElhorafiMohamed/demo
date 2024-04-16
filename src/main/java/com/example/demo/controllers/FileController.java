package com.example.demo.controllers;


import com.example.demo.dao.FileDao;
import com.example.demo.entities.datasource1.File;
import com.example.demo.exceptions.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
public class FileController {
    FileDao storageService;

    //work
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files){
        return storageService.save(files);
    }

    //work
    @GetMapping("/deletes")
    public ResponseEntity<ResponseMessage> deleteFiles(){
        return storageService.deleteAll();
    }

    //work
    @GetMapping("/files/{typeFichier:.+}")
    public List<File> getAllFichierByType(@PathVariable String typeFichier) throws IOException {
        return storageService.getAllFichierByType(typeFichier);
    }

    //work
    @GetMapping("/getAllFichier")
    public List<File> getAllFichier() throws IOException {
        return storageService.getAllFichier();
    }
}

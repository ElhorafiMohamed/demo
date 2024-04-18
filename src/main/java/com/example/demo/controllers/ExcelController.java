package com.example.demo.controllers;


import com.example.demo.dao.ExcelDao;
import com.example.demo.exceptions.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
public class ExcelController {
    ExcelDao excelDao;

    //work
    @PostMapping("/import")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
        return excelDao.importe(files);
    }

}

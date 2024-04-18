package com.example.demo.dao;


import com.example.demo.exceptions.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface ExcelDao {
     ResponseEntity<ResponseMessage> importe(MultipartFile[] files) throws IOException;

}

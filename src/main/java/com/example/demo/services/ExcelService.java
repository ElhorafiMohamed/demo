package com.example.demo.services;


import com.example.demo.dao.ExcelDao;
import com.example.demo.entities.AttachmentType;
import com.example.demo.exceptions.ResponseMessage;
import com.example.demo.repositories.AttachementTypeRepository;
import com.example.demo.utils.DataBaseUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ExcelService implements ExcelDao {

    private final AttachementTypeRepository attachementTypeRepository;
    private final DataBaseUtil dataBaseUtil;

    @Override
    public ResponseEntity<ResponseMessage> importe(MultipartFile[] files) throws IOException {
        FileInputStream file = new FileInputStream(new File("upload/sheet/example.xlsx"));
        Set<String> namesOfTable = dataBaseUtil.getAllTableNames();
        for (String nameSheet : namesOfTable) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet(nameSheet);
            if (sheet != null && nameSheet == "AttachmentType") {
                for (Row row : sheet) {
                    AttachmentType attachmentType = new AttachmentType();
                    attachmentType.setName(row.getCell(1).toString());
                    attachmentType.setAbrv(row.getCell(2).toString());
                    attachementTypeRepository.save(attachmentType);
                }
            }


            Map<Integer, List<String>> data = new HashMap<>();

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Fichier enregistré"));
    }

}


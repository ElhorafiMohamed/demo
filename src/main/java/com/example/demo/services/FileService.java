package com.example.demo.services;


import com.example.demo.dao.FileDao;
import com.example.demo.entities.File;
import com.example.demo.exceptions.ResponseMessage;
import com.example.demo.repositories.FileRepository;
import com.example.demo.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService implements FileDao {
    private final Path root = Paths.get("upload");
    private final FileUtil fileUtil;
    private final FileRepository fileRepository;

    @Override
    public ResponseEntity<ResponseMessage> save(MultipartFile[] files) {
        try {
            fileUtil.createDossier("upload");
            for (MultipartFile file : files) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                if (fileName.contains("..")) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Invalid file name: " + fileName));
                }

                String type = fileUtil.getTypeOfFile(file);
                if (!fileUtil.findFichierByName(fileName, type)) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Fichier: '" + fileName + "' est déjà enregistré"));
                }

                String filePath = "upload/" + type;
                fileUtil.createDossier(filePath);
                fileUtil.saveFichier(file, filePath);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Fichier enregistré"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("Could not store the file. Error: " + e.getMessage()));
        }
    }


    @Override
    public ResponseEntity<ResponseMessage> deleteAll() {
        try {
            FileSystemUtils.deleteRecursively(root.toFile());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Fichier supprimer"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("Could not delete files. Error: " + e.getMessage()));
        }
    }

    //type existe est 7 : csv doc pdf plain(text) png sheet jpeg
    @Override
    public List<String> getAllFichierByType(String string) throws IOException {
            List<String> document = Files.walk(Paths.get("upload/"+string), 1)
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
            return document;
    }

    @Override
    public List<String> getAllFichier() throws IOException {
        List<String> document = getAllFichierByType("pdf");
        List<String> document1 = getAllFichierByType("csv");
        List<String> document2 = getAllFichierByType("plain");
        List<String> document3 = getAllFichierByType("sheet");
        System.out.println(document);
        System.out.println(document1);
        System.out.println(document2);
        System.out.println(document3);
        return document;
    }
}


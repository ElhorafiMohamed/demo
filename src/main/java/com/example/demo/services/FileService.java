package com.example.demo.services;


import com.example.demo.dao.FileDao;
import com.example.demo.entities.AttachmentType;
import com.example.demo.entities.File;
import com.example.demo.exceptions.ResponseMessage;
import com.example.demo.repositories.AttachementTypeRepository;
import com.example.demo.repositories.FileRepository;
import com.example.demo.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService implements FileDao {
    private final Path root = Paths.get("upload");
    private final FileUtil fileUtil;
    private final FileRepository fileRepository;
    private final AttachementTypeRepository attachementTypeRepository;

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

                Optional<AttachmentType> attachmentType = attachementTypeRepository.findByAbrv(type);
                fileRepository.save(File.builder().name(fileName).url(filePath).attachmentType(attachmentType.get()).build());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Fichier enregistré"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("Could not store the file. Error: " + e.getMessage()));
        }
    }

    @Override
    public List<File> getAllFichier()  {
            List<File> files = fileRepository.findAll();
            List<File> filteredFiles = files.stream()
                    .filter(file -> !file.getIsDeleted())
                    .collect(Collectors.toList());
        return filteredFiles;
    }


    @Override
    public List<File> getAllFichierByType(String string) {
            List<File> files = fileRepository.findAll();
            Optional<AttachmentType> attachmentType = attachementTypeRepository.findByAbrv(string);
            List<File> filteredFiles = files.stream()
                    .filter(file -> (file.getAttachmentType() == attachmentType.get()) && !file.getIsDeleted())
                    .collect(Collectors.toList());
        return filteredFiles;
    }


    @Override
    public ResponseEntity<ResponseMessage> deleteAll() {
        try {
            fileRepository.updateAllFiles(true);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Fichier supprimer"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("Could not delete files. Error: " + e.getMessage()));
        }
    }




}


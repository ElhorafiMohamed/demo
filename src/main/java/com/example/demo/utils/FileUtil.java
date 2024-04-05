package com.example.demo.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class FileUtil {

    public static String getTypeOfFile(MultipartFile file){
        String filename = file.getContentType().replaceAll("/", ".");
        String[] parts = filename.split("\\.");
        String lastWord = parts[parts.length - 1];
        return lastWord;
    }

    public static void createDossier(String path) throws IOException {
        Path roots = Paths.get(path);
        if (!Files.exists(roots)) {
            Files.createDirectory(roots);
        }
    }

    public static void saveFichier(MultipartFile file ,String path) throws IOException {
        Path roots = Paths.get(path);
        String nameFichier = file.getOriginalFilename();
        Files.copy(file.getInputStream(), roots.resolve(nameFichier));
    }

    public static boolean findFichierByName(String fileName,String type ) throws IOException {
        Path roots = Paths.get("upload/"+type);
        Path file = roots.resolve(StringUtils.cleanPath(fileName));
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return false;
        } else {
            return true;
        }
    }
}

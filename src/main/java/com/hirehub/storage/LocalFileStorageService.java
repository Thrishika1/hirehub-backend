package com.hirehub.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class LocalFileStorageService implements FileStorageService{

    private final Path uploadPath;

    public LocalFileStorageService(@Value("${app.storage.upload-dir}") String uploadDir){

        //converts to absolute path
        this.uploadPath = Path.of(uploadDir).toAbsolutePath().normalize();
    }

    @Override
    public String store(MultipartFile file, String directory){
        try{

            Path targetDirectory = uploadPath.resolve(directory).normalize();

            //creates the directory represented by targetDirectory if it doesn't already exist.
            Files.createDirectories(targetDirectory);

            String originalFileName = file.getOriginalFilename();

            String extension = "";

            if(originalFileName!=null && originalFileName.contains(".")){
                extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }

            String storedFileName = UUID.randomUUID() + extension;

            Path targetPath = targetDirectory.resolve(storedFileName).normalize();

            Files.copy(file.getInputStream(),targetPath, StandardCopyOption.REPLACE_EXISTING);

            //converts to relative path
            return uploadPath.relativize(targetPath).toString();


        }catch(IOException exception){
            throw new RuntimeException("Failed to store file", exception);
        }
    }

    public void delete(String filePath){
        try{
            Path targetPath = uploadPath.resolve(filePath).normalize();

            Files.deleteIfExists(targetPath);

        }catch (IOException exception){
            throw new RuntimeException("Failed to delete file", exception);
        }
    }
}

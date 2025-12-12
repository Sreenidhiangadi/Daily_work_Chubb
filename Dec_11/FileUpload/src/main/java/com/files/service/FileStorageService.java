package com.files.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.files.entity.FileEntity;
import com.files.repository.FileRepository;

import java.nio.file.*;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final FileRepository fileRepository;

    public FileStorageService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileEntity saveFile(MultipartFile file) throws Exception {
        if (!file.getContentType().equals("application/json")) {
            throw new Exception("Only JSON files are allowed");
        }

        Files.createDirectories(Paths.get(uploadDir));

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + "/" + fileName);

        Files.write(filePath, file.getBytes());

        FileEntity entity = new FileEntity();
        entity.setFileName(fileName);
        entity.setFileSize(file.getSize());
        entity.setFileType(file.getContentType());
        entity.setFilePath(filePath.toString());

        return fileRepository.save(entity);
    }

    public FileEntity getFile(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    public byte[] downloadFile(Long id) throws Exception {
        FileEntity entity = getFile(id);
        return Files.readAllBytes(Paths.get(entity.getFilePath()));
    }
}

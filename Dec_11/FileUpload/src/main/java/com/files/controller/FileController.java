package com.files.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.files.entity.FileEntity;
import com.files.service.FileStorageService;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileStorageService fileService;

    public FileController(FileStorageService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileEntity saved = fileService.saveFile(file);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable Long id) {
        FileEntity file = fileService.getFile(id);
        return file != null ? ResponseEntity.ok(file)
                            : ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(@PathVariable Long id) {
        try {
            FileEntity entity = fileService.getFile(id);
            byte[] data = fileService.downloadFile(id);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + entity.getFileName() + "\"")
                    .contentType(MediaType.parseMediaType(entity.getFileType()))
                    .body(data);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Download failed");
        }
    }
}

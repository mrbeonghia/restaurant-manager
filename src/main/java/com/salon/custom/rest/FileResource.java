package com.salon.custom.rest;

import com.salon.custom.dto.FileDTO;
import com.salon.custom.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileResource {

    @Autowired
    FileService fileService;

    @PostMapping("v1/admin/uploadFile")
    public ResponseEntity<FileDTO> uploadFile(@RequestParam(name = "file") MultipartFile file) {
        FileDTO fileDTO = fileService.uploadFile(file, "image", "image");
        return ResponseEntity.ok().body(fileDTO);
    }
}

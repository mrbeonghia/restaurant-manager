package com.salon.custom.service;

import com.salon.custom.dto.FileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class FileService {
    @Value("${domain}")
    private String domain;

    public FileDTO uploadFile(MultipartFile file, String type, String folder) {
        try {
            String contentType = file.getContentType();
            String tail = contentType.split("/")[1];
            byte[] bytes = file.getBytes();
            String fileNameSave = UUID.randomUUID().toString() + UUID.randomUUID().toString() + "." + tail;
            return upload(bytes, type, fileNameSave, folder);
        } catch(Exception e) {
            return null;
        }

    }

    private FileDTO upload(byte[] bytes, String type, String fileName, String folder) throws Exception{
        String fileNameSave = UUID.randomUUID().toString() + UUID.randomUUID().toString() + fileName;
        File uploadRootDir = new File("upload" + File.separator + folder);
        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + fileNameSave);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();

        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileUrl(domain + "upload/" + folder + "/" + fileNameSave);
        fileDTO.setType(type);
        return fileDTO;
    }

    public FileDTO uploadFileBytes(byte[] bytes, String type, String fileName, String folder) {
        try {
            return upload(bytes, type, fileName, folder);
        } catch(Exception e) {
            return null;
        }

    }

}

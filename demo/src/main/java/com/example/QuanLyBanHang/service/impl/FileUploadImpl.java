package com.example.QuanLyBanHang.service.impl;

//import com.cloudinary.Cloudinary;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.QuanLyBanHang.service.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class FileUploadImpl implements FileUpload {
    @Autowired
    private final Cloudinary cloudinary;
    @Override
    public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File converFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(converFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return converFile;


    }
    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
            try
            {
                File uploadFile = convertMultiPartToFile(multipartFile);
                @SuppressWarnings("rawtypes")
                        Map uploadResult = cloudinary.uploader().upload(uploadFile, ObjectUtils.emptyMap());
                uploadFile.delete();
                return uploadResult.get("url").toString();
            }
            catch (Exception e)
            {
                throw  new RuntimeException(e);
            }

    }



}

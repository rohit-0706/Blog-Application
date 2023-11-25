package com.rohit.javablogapplication.service.Impl;

import com.rohit.javablogapplication.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();

        String randomId = UUID.randomUUID().toString();

        assert name != null;
        String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

        String filePath = path + File.separator + fileName1;

        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        return new FileInputStream(fullPath);
    }
}

package com.blogapplication.services.impl;

import com.blogapplication.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {

        //file name
        String name=file.getOriginalFilename();

        //random id
        String randomUUID= UUID.randomUUID().toString();
        String fileName1=randomUUID.concat(name.substring(name.lastIndexOf('.')));

        //fullPath
        String filePath=path+ File.separator+fileName1;

        //create folder if not present
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        InputStream is=new FileInputStream(fullPath);
        //db logic to return inputstream
        return is;
    }
}

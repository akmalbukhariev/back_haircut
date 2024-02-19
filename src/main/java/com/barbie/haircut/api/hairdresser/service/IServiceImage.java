package com.barbie.haircut.api.hairdresser.service;

import com.barbie.haircut.api.constant.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IServiceImage {

    @Value("${upload.dir}")
    private String uploadDir;

    public Path getFullPath(String imageFilePath){

        Path  file = Paths.get(imageFilePath);

            if(Files.exists(file))
            {
                return file;
            }
            else {
                return null;
            }
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException{
        // Normalize file name
        if(multipartFile == null) return  null;

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                return  null;
            }

            Date date = new Date();
            String strTimeName  = String.valueOf(date.getTime()) +
                    "." + extractExt(multipartFile.getOriginalFilename());



            Path directory = Paths.get(uploadDir);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            Path targetFileLocation = directory.resolve(strTimeName);
            try (OutputStream os = Files.newOutputStream(targetFileLocation)) {
                os.write(multipartFile.getBytes());
            }

            return new UploadFile(fileName, targetFileLocation.toString());
        } catch (IOException ex) {
            throw new IOException(ex); //FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException
    {
            List<UploadFile> storeFilesResult = new ArrayList<>();
            for (MultipartFile multipartFile: multipartFiles)
            {
                if(!multipartFile.isEmpty())
                {
                    storeFilesResult.add(storeFile(multipartFile));
                }
            }
            return  storeFilesResult;
    }

    private  String extractExt(String originalFileName)
    {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }

    public void deletestoreFile(String storeImageFilePath)
    {
        Path file = Path.of(storeImageFilePath);
        try {
            if(Files.exists(file)) {
                Files.deleteIfExists(file);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error"+e.getMessage());
        }
    }
}

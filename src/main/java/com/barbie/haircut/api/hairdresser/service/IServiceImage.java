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
    private static final  Path root = Constant.UPLOAD_DIRECTORY;

    @Value("${upload.dir}")
    private String uploadDir;

    public Resource getFullPath(String fileName){

        Path  file = root.resolve(fileName);

        if(!Files.exists(file)) return null;

        Resource resource = null;
        try {
            resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable())
            {
                return resource;
            }
            else {
                throw new RuntimeException("Could not read the files");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error" + e.getMessage());
        }
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException{
        // Normalize file name
        if(multipartFile == null) return  null;

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                //throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Date date = new Date();
            String strTimeName  = String.valueOf(date.getTime()) +
                    "." + extractExt(multipartFile.getOriginalFilename());

            Path targetLocation = root.resolve(strTimeName);

            Path directory = Paths.get(uploadDir);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
 
            Path filepath = directory.resolve(strTimeName);
            try (OutputStream os = Files.newOutputStream(filepath)) {
                os.write(multipartFile.getBytes());
            }

            //Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return new UploadFile(fileName, strTimeName);
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

    public void deletestoreFile(String storeFilename)
    {
        Path file = root.resolve(storeFilename);
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

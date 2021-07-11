package com.attijari.web.Utils;

import org.apache.commons.io.FilenameUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class StorageService {

    public Resource loadFile(String filename) {
        try {
            File fil = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("")).getFile());
            Path filePath = Paths.get(fil.getPath());
            Path rootLocation = Paths.get(fil.getPath());
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFileDeblocage(String filename) {
        try {
            File fil = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("")).getFile());
            Path filePath = Paths.get(fil.getPath());
            Path rootLocation = Paths.get(fil.getPath());
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public String storeFileDeblocage(String bytes, String path) {
        File file = new File(path);
        try {
            try (FileOutputStream fos = new FileOutputStream(file);) {
                byte[] decoder = Base64.getDecoder().decode(bytes);
                fos.write(decoder);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String storeContrat(String path) throws IOException {
        File fil = new File(getClass().getClassLoader().getResource("Bulletin de souscription PRIVILEGES BLEDI.pdf").getFile());
        Path filePath = Paths.get(fil.getPath());
        byte[] bytes = Files.readAllBytes(filePath);
        File file = new File(path);
        try {
            try (FileOutputStream fos = new FileOutputStream(file);) {
                byte[] decoder = Base64.getDecoder().decode(bytes);
                fos.write(decoder);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] dowanloadContrat(HttpServletResponse response) throws IOException {
        final byte[] buf = new byte[1024];
        File file = new File(getClass().getClassLoader().getResource("Bulletin de souscription PRIVILEGES BLEDI.pdf").getFile());
        Path filePath = Paths.get(file.getPath());
        byte[] fileContent = Files.readAllBytes(filePath);
        response.setHeader("Content-Disposition", file.getName());
        response.setContentType("application/pdf");
        return fileContent;
    }
}

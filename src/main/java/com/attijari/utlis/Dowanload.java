package com.attijari.utlis;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.integration.ftp.session.FtpSession;
import org.springframework.stereotype.Component;

@Component
public class Dowanload {
    @Autowired
    FtpServer ftpSeurveur;

    public byte[] dowanloadFile(String pathFile,HttpServletResponse response)  throws IOException{
        final byte[] buf = new byte[1024];
        FtpSession session = ftpSeurveur.getFactory().getSession();
        File file = new File("dowanloadFile.pdf");
        OutputStream rs = new FileOutputStream(file);
        session.read(pathFile, rs);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        OutputStream bufferedOutputStream = response.getOutputStream();
        response.setHeader("Content-Disposition", file.getName());
        response.setContentType("application/pdf");
        int n = 0;
        while ((n = in.read(buf)) > 0) {
            bufferedOutputStream.write(buf, 0, n);
        }
        bufferedOutputStream.flush();
        file.delete();
        in.close();
        return buf;

    }
    public byte[] dowanloadFatca(HttpServletResponse response)  throws IOException{
        final byte[] buf = new byte[1024];
        File file = new File(getClass().getClassLoader().getResource("fatca.pdf").getFile());
        Path filePath = Paths.get(file.getPath());
        byte[] fileContent = Files.readAllBytes(filePath);
        response.setHeader("Content-Disposition", file.getName());
        response.setContentType("application/pdf");
      //  response.setContentType("application/doc"); // pour fiche type doc
        return fileContent;

    }
}

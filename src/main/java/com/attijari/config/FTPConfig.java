package com.attijari.config;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value = {"prod","recette"})
public class FTPConfig {

    @Bean
    public FTPClient ftpClient(){
        try {
            return new FTPClient();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}

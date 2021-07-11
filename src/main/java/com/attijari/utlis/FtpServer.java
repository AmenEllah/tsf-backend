package com.attijari.utlis;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.stereotype.Component;
@Component
public class FtpServer {

    private final Logger log = LoggerFactory.getLogger(FtpServer.class);

    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.user}")
    private String userName;
    @Value("${ftp.password}")
    private String password;

    public DefaultFtpSessionFactory getFactory() {

        log.debug("Request to create sessionFactory with ftp server on : {}:{}", host, port);
        DefaultFtpSessionFactory factory;
        factory = new DefaultFtpSessionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setClientMode(FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE);
        factory.setUsername(userName);
        factory.setPassword(password);
        return factory;

    }

    public FtpServer() {
        super();
        // TODO Auto-generated constructor stub
    }

}

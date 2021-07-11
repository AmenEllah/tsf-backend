package com.attijari.config;

import com.attijari.service.CustomerClient;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;


@Configuration
public class CustomerConfiguration extends HttpUrlConnectionMessageSender {

    @Value("${middleware.protocol}")
    private String protocol;

    @Value("${middleware.host}")
    private String host;

    @Value("${middleware.port}")
    private String port;

    @Value("${middleware.uri.aif}")
    private String uri;

    private String username;
    private String password;

    public CustomerConfiguration(@Value("${middleware.username}")
                                     String username, @Value("${middleware.password}") String password) {
        this.username = username;
        this.password = password;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.attijari.service.wsdl.tn.com.abtservicemiddleware.aif.ws");
        return marshaller;
    }

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor security = new Wss4jSecurityInterceptor();

        // Adds "Timestamp" and "UsernameToken" sections in SOAP header
        security.setSecurementActions(WSHandlerConstants.USERNAME_TOKEN);

        // Set values for "UsernameToken" sections in SOAP header
        security.setSecurementPasswordType(WSConstants.PW_DIGEST);
        security.setSecurementUsername(username);
        security.setSecurementPassword(password);
        return security;
    }

    @Bean
    public CustomerClient customerClient(Jaxb2Marshaller marshaller) {
        CustomerClient client = new CustomerClient();
        client.setDefaultUri(getUri());
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        client.setInterceptors(new ClientInterceptor[]{securityInterceptor()});
        return client;
    }


    private String getUri() {
        return protocol + host + ":" + port + "/" + uri + "/AIFSOAPWS";
    }


}

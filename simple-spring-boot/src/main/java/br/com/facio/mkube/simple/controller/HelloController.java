package br.com.facio.mkube.simple.controller;

import br.com.facio.mkube.simple.feign.NginxClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger logger = LogManager.getLogger(HelloController.class);
    
    @Autowired
    private NginxClient nginxClient;

    @GetMapping("/fetch-hello")
    public String fetchHello() {
        logger.info("Received request to /fetch-hello");
        String response = nginxClient.getHello();
        logger.info("Response from Nginx service: " + response);
        return response;
    }
}

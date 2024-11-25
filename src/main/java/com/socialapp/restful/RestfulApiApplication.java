package com.socialapp.restful;

import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulApiApplication {

    private static final Logger logger = LogManager.getLogger(RestfulApiApplication.class.getName());

    @Autowired
    private AppConfig appConfig;

    public static void main(String[] args) {
        SpringApplication.run(RestfulApiApplication.class, args);

        logger.info("This is an info message");
        logger.debug("This is an debug message");
        logger.error("This is an error message");
    }

    @PostConstruct
    public void onStartup() {
        // Your startup logic here
        logger.info("ON-STARTUP-LOGIC @PostConstruct");
    }
}

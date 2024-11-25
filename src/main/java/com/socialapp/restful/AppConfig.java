package com.socialapp.restful;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author JulianFisla
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class AppConfig {

    private static final Logger logger = LogManager.getLogger(AppConfig.class.getName());

    @Autowired
    Environment env;

    /**
     *
     * @param name
     * @return
     */
    public String getProperty(String name) {
        return env.getProperty(name);
    }
}

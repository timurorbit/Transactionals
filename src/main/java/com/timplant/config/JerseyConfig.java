package com.timplant.config;


import com.timplant.controllers.TransactionsController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {

        // Controllers
        register(TransactionsController.class);
    }
}
package com.ecomsuite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// The @SpringBootApplication annotation enables auto-configuration,
// component scanning, and configuration properties.
@SpringBootApplication
public class EcomSuiteApplication {

    public static void main(String[] args) {
        // This method boots up the entire Spring context,
        // including the embedded web server and the Actuator endpoints.
        SpringApplication.run(EcomSuiteApplication.class, args);
    }
}

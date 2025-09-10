package org.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EventProducerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventProducerServiceApplication.class, args);
    }
}

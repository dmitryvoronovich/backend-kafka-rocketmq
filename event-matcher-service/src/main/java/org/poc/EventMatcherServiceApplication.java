package org.poc;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ConfigurationPropertiesScan
@Import(RocketMQAutoConfiguration.class)
@Slf4j
public class EventMatcherServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventMatcherServiceApplication.class, args);
    }
}

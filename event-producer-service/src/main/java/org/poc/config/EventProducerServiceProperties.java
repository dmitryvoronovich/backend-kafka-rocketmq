package org.poc.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "event.producer.service")
public class EventProducerServiceProperties {

    private String eventOutcomeTopic;
}

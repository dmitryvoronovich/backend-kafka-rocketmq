package org.poc.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "event.consumer.service")
public class EventMatcherServiceProperties {

    private final String eventOutcomeTopic;
    private final String betSettlementsTopic;
}

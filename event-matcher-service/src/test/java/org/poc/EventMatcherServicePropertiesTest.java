package org.poc;

import org.junit.jupiter.api.Test;
import org.poc.config.EventMatcherServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@EnableConfigurationProperties(EventMatcherServiceProperties.class)
@TestPropertySource(properties = {
        "event.consumer.service.event-outcome-topic=test-outcome-topic",
        "event.consumer.service.bet-settlements-topic=test-settlements-topic",
})
class EventMatcherServicePropertiesTest {

    @Autowired
    private EventMatcherServiceProperties properties;

    @Test
    void shouldBindTopicFromProperties() {
        assertNotNull(properties);
        assertAll("verify all properties used",
                () -> assertEquals("test-outcome-topic", properties.getEventOutcomeTopic()),
                () -> assertEquals("test-settlements-topic", properties.getBetSettlementsTopic())
        );
    }
}

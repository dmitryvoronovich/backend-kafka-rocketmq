package org.poc.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@EnableConfigurationProperties(EventProducerServiceProperties.class)
@TestPropertySource(properties = {
        "event.producer.service.event-outcome-topic=test-topic"
})
class EventProducerServicePropertiesTest {

    @Autowired
    private EventProducerServiceProperties properties;

    @Test
    void shouldBindTopicFromProperties() {
        assertNotNull(properties);
        assertEquals("test-topic", properties.getEventOutcomeTopic());
    }
}

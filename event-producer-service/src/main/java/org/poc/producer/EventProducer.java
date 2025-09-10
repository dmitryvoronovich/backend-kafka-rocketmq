package org.poc.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.poc.config.EventProducerServiceProperties;
import org.poc.domain.model.EventOutcome;
import org.poc.service.EventOutcomeConverter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventProducer {

    private final EventProducerServiceProperties properties;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final EventOutcomeConverter eventOutcomeConverter;

    public void produce(EventOutcome eventOutcome) {
        String message = eventOutcomeConverter.convertEventOutcome(eventOutcome);

        log.info("Producing message to topic: message={}, topic={}", message, properties.getEventOutcomeTopic());
        kafkaTemplate.send(properties.getEventOutcomeTopic(), message);
    }

}

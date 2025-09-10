package org.poc.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.poc.config.EventMatcherServiceProperties;
import org.poc.domain.entity.Bet;
import org.poc.domain.model.EventOutcome;
import org.poc.producer.BetSettlementsProducer;
import org.poc.repository.BetRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class EventOutcomeConsumer {

    public final EventMatcherServiceProperties properties;
    private final ObjectMapper objectMapper;
    private final BetRepository betRepository;
    private final BetSettlementsProducer betSettlementsProducer;

    @KafkaListener(
            topics = "#{__listener.properties.eventOutcomeTopic}",
            groupId = "bet-consumer-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(String message, Acknowledgment ack) {
        log.info("Received message from Kafka: message={}, topic={}", message, properties.getEventOutcomeTopic());

        EventOutcome eventOutcome = convertToEventOutcome(message);
        List<Bet> bets = betRepository.findByEventId(eventOutcome.getEventId());

        try {
            produceToRocketMq(bets);
            log.info("Produced all bets to RocketMQ: topic={}", properties.getBetSettlementsTopic());
            ack.acknowledge();        // commit Kafka topic offset only after sent to RocketMQ
            log.info("Commited offsets: topic={}", properties.getBetSettlementsTopic());
        } catch (Exception e) {
            String msg = String.format("Failed to process message, will not commit offset. message=%s", message);
            throw new RuntimeException(msg, e);
        }
    }

    private void produceToRocketMq(List<Bet> bets) {
        for (Bet bet : bets) {
            log.info("Bet to be sent to bet-settlements: bet={}", bet);
            //should be synchronous, consider sending in batch
            betSettlementsProducer.sendMessage(bet);
        }
    }

    private EventOutcome convertToEventOutcome(String message) {
        try {
            return objectMapper.readValue(message, EventOutcome.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("Failed to parse message to object: message=%s", message), e);
        }
    }
}

package org.poc.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.poc.config.EventMatcherServiceProperties;
import org.poc.domain.entity.Bet;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BetSettlementsProducer {

    private final EventMatcherServiceProperties properties;
    private final RocketMQTemplate rocketMQTemplate;

    public void sendMessage(Bet bet) {
        String topic = properties.getBetSettlementsTopic();
        rocketMQTemplate.convertAndSend(topic, bet);
        log.info("Sent message to topic: message={}, topic={}", bet, topic);
    }
}

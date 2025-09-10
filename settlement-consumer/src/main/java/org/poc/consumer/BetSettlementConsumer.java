package org.poc.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(
        consumerGroup = "${rocketmq.consumer.group}",
        topic = "${settlement.consumer.bet-settlements-topic}"
)
public class BetSettlementConsumer implements RocketMQListener<String> {

    private final String topic;

    public BetSettlementConsumer(@Value("${settlement.consumer.bet-settlements-topic}") String topic) {
        this.topic = topic;
    }

    @Override
    public void onMessage(String message) {
        log.info("Consumed message: bet-settlement={}, topic={}", message, topic);
    }
}

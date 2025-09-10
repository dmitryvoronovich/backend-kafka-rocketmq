package org.poc.controller;

import lombok.RequiredArgsConstructor;
import org.poc.domain.model.EventOutcome;
import org.poc.producer.EventProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventProducer producer;

    @PostMapping("/post")
    public String postEventOutcome(@RequestBody EventOutcome eventOutcome) {
        producer.produce(eventOutcome);
        return "Published event outcome with ID " + eventOutcome.getEventId();
    }

}

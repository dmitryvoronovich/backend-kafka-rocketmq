package org.poc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.poc.domain.model.EventOutcome;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventOutcomeConverter {
    private final ObjectMapper objectMapper;

    public String convertEventOutcome(EventOutcome eventOutcome) {
        try {
            return objectMapper.writeValueAsString(eventOutcome);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert eventOutcome", e);
        }
    }
}

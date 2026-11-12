package com.ticketflow.eventservice;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final Map<Long, EventResponse> events = Map.of(
        1L, new EventResponse(1L, "Concert Casablanca",
            "Casablanca", 100, new BigDecimal("250.00")),
        2L, new EventResponse(2L, "Festival de musique",
            "Rabat", 50, new BigDecimal("150.00"))
    );

    @GetMapping("/{id}")
    public EventResponse getEvent(@PathVariable Long id) {
        EventResponse event = events.get(id);
        if (event == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Événement introuvable");
        }
        return event;
    }
}

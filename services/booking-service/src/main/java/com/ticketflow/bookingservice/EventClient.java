package com.ticketflow.bookingservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-service")
public interface EventClient {

    @GetMapping("/api/v1/events/{id}")
    EventResponse getEvent(@PathVariable("id") Long id);
}

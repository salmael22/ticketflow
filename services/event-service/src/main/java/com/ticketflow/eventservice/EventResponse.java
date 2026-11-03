package com.ticketflow.eventservice;

import java.math.BigDecimal;

public record EventResponse(
    Long id,
    String name,
    String location,
    int availablePlaces,
    BigDecimal price
) {}
